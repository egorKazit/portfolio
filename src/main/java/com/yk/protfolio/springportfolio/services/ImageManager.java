package com.yk.protfolio.springportfolio.services;

import java.util.List;
import java.util.function.Function;

public interface ImageManager {
    <Entity> void uploadImage(List<Entity> entities, Function<Entity, String> getPictureFunction, Function<Entity, byte[]> getImageFunction);
}
