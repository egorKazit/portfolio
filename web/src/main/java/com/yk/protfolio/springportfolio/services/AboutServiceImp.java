package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.AboutDAO;
import com.yk.schema.About;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to work on about
 */
@Service
public class AboutServiceImp implements AboutService {

    @Autowired
    private AboutDAO aboutDAO;

    @Autowired
    private ImageManager imageManager;

    /**
     * gets general about information
     *
     * @return general information
     */
    @Override
    public About getGeneralAbout() {
        About about = aboutDAO.getAbout(0);
        if (about != null) {
            imageManager.uploadImage(List.of(about), About::getPicture, About::getImage);
            about.setHidden(false);
        }
        return about;
    }

    /**
     * gets about information by id
     *
     * @return about information
     */
    @Override
    public About getDetailedAbout(int id) {
        About about = aboutDAO.getAbout(id);
        if (about != null) {
            imageManager.uploadImage(List.of(about), About::getPicture, About::getImage);
            about.setHidden(true);
        }
        return about;
    }

    /**
     * gets list of about
     *
     * @return list of about
     */
    @Override
    public List<About> getAboutList() {
        List<About> abouts = aboutDAO.getAbouts();
        imageManager.uploadImage(abouts, About::getPicture, About::getImage);
        return abouts;
    }
}
