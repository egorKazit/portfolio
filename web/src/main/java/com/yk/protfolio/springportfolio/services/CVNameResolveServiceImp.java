package com.yk.protfolio.springportfolio.services;

import java.nio.file.Path;
import org.springframework.stereotype.Service;

@Service
public class CVNameResolveServiceImp implements CVNameResolveService {

    @Override
    public String resolveCVName(String rawCVName) {
        return Path.of(rawCVName).getFileName().toString();
    }
}
