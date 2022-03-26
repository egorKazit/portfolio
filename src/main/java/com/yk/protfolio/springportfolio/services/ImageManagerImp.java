package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.utilities.FolderConstants;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
class ImageManagerImp implements ImageManager {

    private static final URL imagesFolderURL = ImageManagerImp.class.getClassLoader().getResource(FolderConstants.IMAGE_OLDER);

    static {
        if (imagesFolderURL != null) {
            File imageFolder = new File(imagesFolderURL.getFile());
            Arrays.stream(Objects.requireNonNull(imageFolder.listFiles())).filter(file -> !file.isDirectory()
                    && !file.getName().equals(FolderConstants.IMAGE_FINAL)).forEach(File::delete);
        }
    }

    @Override
    public <Entity> void uploadImage(List<Entity> entities, Function<Entity, String> getPictureFunction, Function<Entity, byte[]> getImageFunction) {
        File directory = new File(Objects.requireNonNull(imagesFolderURL).getFile());
        if (directory.isDirectory()) {
            List<File> files = List.of(Objects.requireNonNull(directory.listFiles()));
            entities.forEach(entity -> {
                String pictureName = getPictureFunction.apply(entity);
                if (files.stream().noneMatch(file -> file.getName().equals(pictureName))) {
                    try {
                        Files.write(Path.of(new File(imagesFolderURL.getFile(), pictureName).getPath()), getImageFunction.apply(entity));
                    } catch (IOException e) {
                        log.atError().log("Error on file saving: {}", e.getMessage());
                    }
                }
            });
        }
    }

}
