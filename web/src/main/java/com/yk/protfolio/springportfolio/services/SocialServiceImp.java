package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.SocialDAO;
import com.yk.schema.Social;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to work on social
 */
@Service
@AllArgsConstructor
public class SocialServiceImp implements SocialService {

    private SocialDAO socialDAO;
    private ImageManager imageManager;

    /**
     * gets list of socials
     *
     * @return list of socials
     */
    @Override
    public List<Social> getSocials() {
        List<Social> socials = socialDAO.getSocials();
        imageManager.uploadImage(socials, Social::getPicture, Social::getImage);
        return socials;
    }
}
