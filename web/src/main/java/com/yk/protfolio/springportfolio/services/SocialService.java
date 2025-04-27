package com.yk.protfolio.springportfolio.services;

import com.yk.schema.Social;

import java.util.List;

/**
 * Service interface to work on social
 */
public interface SocialService {
    /**
     * gets list of socials
     *
     * @return list of socials
     */
    List<Social> getSocials();
}
