package com.yk.protfolio.springportfolio.services;

import java.util.List;
import java.util.function.Function;

/**
 * Service interface to work on image
 */
public interface ImageManager {
    /**
     * uploads image
     *
     * @param entities           list og entities
     * @param getPictureFunction function to get picture
     * @param getImageFunction   function to get image
     * @param <Entity>           generic entity
     */
    <Entity> void uploadImage(List<Entity> entities, Function<Entity, String> getPictureFunction, Function<Entity, byte[]> getImageFunction);
}
