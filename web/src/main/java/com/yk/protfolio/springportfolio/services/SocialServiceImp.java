package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.SocialDAO;
import com.yk.schema.Social;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to work on social
 */
@Service
public class SocialServiceImp implements SocialService {

    @Autowired
    private SocialDAO socialDAO;

    @Autowired
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
