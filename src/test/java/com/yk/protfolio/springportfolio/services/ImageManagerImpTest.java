package com.yk.protfolio.springportfolio.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test image service
 */
@SpringBootTest
class ImageManagerImpTest {

    @InjectMocks
    ImageManagerImp imageManagerImp;

    @Mock
    CustomProperties customProperties;

    private MockedConstruction<File> mockedFile;
    private MockedStatic<Files> filesMockedStatic;

    /**
     * closes all mocked classes
     */
    @AfterEach
    void teardown() {
        if (mockedFile != null)
            mockedFile.close();
        if (filesMockedStatic != null)
            filesMockedStatic.close();
    }

    @Test
    void initWithNonExistingFolder() {
        when(customProperties.getStaticImageFolder()).thenReturn("");
        File finalFolder = mock(File.class);
        when(finalFolder.isDirectory()).thenReturn(true);
        when(finalFolder.getName()).thenReturn("final");
        File nonFinalFolder = mock(File.class);
        when(nonFinalFolder.isDirectory()).thenReturn(true);
        when(nonFinalFolder.getName()).thenReturn("testFolder");
        File existingFile = mock(File.class);
        when(existingFile.isDirectory()).thenReturn(false);
        when(existingFile.getName()).thenReturn("testFile");
        when(existingFile.delete()).thenReturn(true);
        mockedFile = Mockito.mockConstruction(File.class, (mock, context) -> {
            when(mock.exists()).thenReturn(true);
            when(mock.mkdirs()).thenReturn(true);
            when(mock.listFiles()).thenReturn(new File[]{finalFolder, nonFinalFolder, existingFile});
        });
        imageManagerImp.init();
        verify(finalFolder, times(0)).delete();
        verify(nonFinalFolder, times(0)).delete();
        verify(existingFile, times(1)).delete();
    }

    /**
     * prepares files in folder without new file and checks if new file is created
     */
    @Test
    void uploadImageTestWithExistingPicture() {
        testAll(1, "mocked Picture 1", "mocked Picture 2");
    }

    /**
     * prepares files in folder with new file and checks if new file is not created
     */
    @Test
    void uploadImageTestWithNotExistingPicture() {
        testAll(0, "mocked Picture", "mocked Picture 2");
    }

    /**
     * prepares files in folder with new file, throws an error and checks if the error occurs
     */
    @Test
    void uploadImageTestWithErrorOnWrite() {
        testAll(1, "mocked Picture 1", "mocked Picture 2");
    }

    /**
     * creates array of files based on provided names
     *
     * @param fileNames provided names
     * @return array of files
     */
    private File[] initFiles(String... fileNames) {
        return Arrays.stream(fileNames).map(fileName -> {
            File mockedFileWithCorrectName = Mockito.mock(File.class);
            when(mockedFileWithCorrectName.getName()).thenReturn(fileName);
            return mockedFileWithCorrectName;
        }).toArray(File[]::new);
    }

    /**
     * tests all provided files
     *
     * @param fileNames provided files
     */
    private void testAll(int timesInvoked, String... fileNames) {
        File[] files = initFiles(fileNames);
        mockStaticAndSetExpectation(files, invocationOnMock -> {
            Assertions.assertEquals(MockedEntity.PICTURE, ((Path) invocationOnMock.getArgument(0)).toString());
            Assertions.assertEquals(MockedEntity.IMAGE, invocationOnMock.getArgument(0));
        }, new MockedEntity().getImage(), true);
        imageManagerImp.uploadImage(List.of(new MockedEntity()), MockedEntity::getPicture, MockedEntity::getImage);
        filesMockedStatic.verify(() -> Files.write(Path.of(MockedEntity.PICTURE), MockedEntity.IMAGE), times(timesInvoked));
    }

    /**
     * mocks all static and constructors, set function to chek or to throw an error for provided array of files
     *
     * @param files        array of files
     * @param checker      checker
     * @param imageContent content of image in bytes
     * @param isWithError  flag if error should occurs
     */
    private void mockStaticAndSetExpectation(File[] files, Checker checker, byte[] imageContent, boolean isWithError) {
        mockedFile = Mockito.mockConstruction(File.class, (mock, context) -> {
            when(mock.isDirectory()).thenReturn(true);
            when(mock.listFiles()).thenReturn(files);
            when(mock.getPath()).thenReturn(new MockedEntity().getPicture());
        });
        filesMockedStatic = Mockito.mockStatic(Files.class);
        OngoingStubbing<Files> filesOngoingStubbing = filesMockedStatic.when(() -> Files.write(Path.of(MockedEntity.PICTURE), imageContent));
        if (!isWithError) {
            filesOngoingStubbing.thenAnswer(invocationOnMock -> {
                checker.check(invocationOnMock);
                return null;
            });
        } else {
            filesOngoingStubbing.thenThrow(IOException.class);
        }
        when(customProperties.getStaticImageFolder()).thenReturn("");
    }

    /**
     * Mocked Entity with function to get picture and image
     */
    private static class MockedEntity {

        private static final String PICTURE = "mocked Picture";
        private static final byte[] IMAGE = "mocked Image".getBytes();

        /**
         * gets picture name
         *
         * @return picture name
         */
        public String getPicture() {
            return PICTURE;
        }

        /**
         * gets image content
         *
         * @return image content
         */
        public byte[] getImage() {
            return IMAGE;
        }
    }

    /**
     * Checker interface
     */
    private interface Checker {
        void check(InvocationOnMock invocationOnMock);
    }

}