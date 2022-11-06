package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.VideoDAO;
import com.yk.schema.Video;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

@Service
public class VideoServiceImp implements VideoService {

    private static final int QUOTE = 1024 * 2;

    @Autowired
    private VideoDAO videoDAO;

    @Override
    public byte[] streamVideo(String name, @Nullable HttpRange httpRange) throws IOException {
        return (byte[]) runOperationOnInputStream(name, inputStream -> {
            LocalRange localRange = new LocalRange(httpRange, inputStream);
            if (inputStream.skip(localRange.rangeStart) != localRange.rangeStart) {
                throw new IOException("Out of range");
            }
            byte[] bytes = new byte[(int) (localRange.rangeEnd - localRange.rangeStart)];
            if (inputStream.readNBytes(bytes, 0, (int) (localRange.rangeEnd - localRange.rangeStart)) != -1) {
                return bytes;
            }
            return new byte[0];
        });
    }


    @Override
    public int getContentLength(String name, @Nullable HttpRange httpRange) throws IOException {
        return (int) runOperationOnInputStream(name, inputStream -> {
            LocalRange localRange = new LocalRange(httpRange, inputStream);
            return (int) (localRange.rangeEnd - localRange.rangeStart);
        });
    }

    @Override
    public int getRangeStart(String name, @Nullable HttpRange httpRange) throws IOException {
        return (int) runOperationOnInputStream(name, inputStream -> (int) new LocalRange(httpRange, inputStream).rangeStart);
    }

    @Override
    public int getFileSize(String name) throws IOException {
        return (int) runOperationOnInputStream(name, InputStream::available);

    }

    private Object runOperationOnInputStream(String name, RunnableOnInputStream runnableOnInputStream) throws IOException {
        Video video = videoDAO.getVideoByName(name);
        if (video == null) {
            throw new IOException("No record for name");
        }
        try (InputStream inputStream = new FileInputStream(video.getPath())) {
            return runnableOnInputStream.runOperationOnStream(inputStream);
        }
    }

    @FunctionalInterface
    private interface RunnableOnInputStream {
        Object runOperationOnStream(InputStream inputStream) throws IOException;
    }

    private static class LocalRange {
        private long rangeStart;
        private long rangeEnd;

        private LocalRange(HttpRange httpRange, InputStream inputStream) throws IOException {
            rangeStart = 0;
            rangeEnd = inputStream.available();
            if (httpRange != null) {
                rangeStart = httpRange.getRangeStart(0);
                rangeEnd = httpRange.getRangeEnd(-1);
                if (rangeEnd < 0) {
                    rangeEnd = Math.min(rangeStart + QUOTE, inputStream.available());
                }
            }
        }
    }

}
