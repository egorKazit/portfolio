package com.yk.protfolio.springportfolio.components;

import com.yk.protfolio.springportfolio.services.SocialService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Social wrapper to keep SocialService as singleton
 */
@Component
public class SocialWrapper {

    @Getter
    private static SocialService socialService;

    /**
     * Constructor with initialization SocialService
     *
     * @param socialService SocialService instance
     */
    @Autowired
    private SocialWrapper(SocialService socialService) {
        SocialWrapper.socialService = socialService;
    }

}
