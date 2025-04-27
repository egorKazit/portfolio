package com.yk.protfolio.springportfolio.services;

import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class CVNameResolveServiceImp implements CVNameResolveService {

    @Override
    public String resolveCVName(String rawCVName) {
        return Path.of(rawCVName).getFileName().toString();
    }
}
