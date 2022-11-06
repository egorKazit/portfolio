package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.services.VideoService;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@Log4j2
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/video/{name}")
    public ResponseEntity<Resource> getVideo(@PathVariable String name, @RequestHeader HttpHeaders headers) throws IOException {
        headers.getRange();
        HttpRange httpRange = !headers.getRange().isEmpty() ? headers.getRange().get(0) : null;
        byte[] bytes = videoService.streamVideo(name, httpRange);
        if (httpRange != null) {
            int rangeStart = videoService.getRangeStart(name, httpRange);
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
                    .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(bytes.length))
                    .header(HttpHeaders.CONTENT_RANGE, "bytes "
                            + rangeStart + "-" + (rangeStart + bytes.length)
                            + "/" + videoService.getFileSize(name))
                    .body(new ByteArrayResource(bytes));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
                    .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(videoService.getContentLength(name, null)))
                    .body(new ByteArrayResource(bytes));
        }

    }

}
