package com.yk.protfolio.springportfolio.services;

import java.io.IOException;
import org.springframework.http.HttpRange;
import reactor.util.annotation.Nullable;

public interface VideoService {
    byte[] streamVideo(String name, @Nullable HttpRange httpRange) throws IOException;

    int getContentLength(String name, @Nullable HttpRange httpRange) throws IOException;

    int getRangeStart(String name, @Nullable HttpRange httpRange) throws IOException;

    int getFileSize(String name) throws IOException;

}
