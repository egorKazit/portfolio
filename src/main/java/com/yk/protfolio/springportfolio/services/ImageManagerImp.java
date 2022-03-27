package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import com.yk.protfolio.springportfolio.utilities.FolderConstants;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Configuration
class ImageManagerImp implements ImageManager {

    @Autowired
    private CustomProperties customProperties;

    @PostConstruct
    public void init() {
        File imageFolder = new File(customProperties.getStaticImageFolder());
        if (!imageFolder.exists()) {
            imageFolder.mkdir();
        }
        if (imageFolder.listFiles() != null)
            Arrays.stream(Objects.requireNonNull(imageFolder.listFiles())).filter(file -> !file.isDirectory()
                    && !file.getName().equals(FolderConstants.IMAGE_FINAL)).forEach(File::delete);
    }

    @Override
    public <Entity> void uploadImage(List<Entity> entities, Function<Entity, String> getPictureFunction, Function<Entity, byte[]> getImageFunction) {
        File directory = new File(customProperties.getStaticImageFolder());
        if (directory.isDirectory()) {
            List<File> files = List.of(Objects.requireNonNull(directory.listFiles()));
            entities.forEach(entity -> {
                String pictureName = getPictureFunction.apply(entity);
                if (files.stream().noneMatch(file -> file.getName().equals(pictureName))) {
                    try {
                        Files.write(Path.of(new File(customProperties.getStaticImageFolder(), pictureName).getPath()), getImageFunction.apply(entity));
                    } catch (IOException e) {
                        log.atError().log("Error on file saving: {}", e.getMessage());
                    }
                }
            });
        }
    }

}