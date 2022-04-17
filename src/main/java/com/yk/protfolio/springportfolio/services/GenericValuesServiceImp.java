package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import com.yk.protfolio.springportfolio.persistence.GeneralValueDAO;
import com.yk.protfolio.springportfolio.schema.GeneralValue;
import com.yk.protfolio.springportfolio.utilities.GeneralInfoConstants;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to control text from back-end side
 */
@Service
public class GenericValuesServiceImp implements GenericValuesService {

    @Autowired
    private GeneralValueDAO generalValueDAO;

    @Autowired
    private CustomProperties customProperties;

    @Override
    public String getPortfolio() {
        return getElementByName(GeneralInfoConstants.MAIN_NAME, "Portfolio");
    }

    @Override
    public String getHome() {
        return getElementByName(GeneralInfoConstants.HOME, "Home");
    }

    @Override
    public String getAbout() {
        return getElementByName(GeneralInfoConstants.ABOUT, "About");
    }

    @Override
    public String getWork() {
        return getElementByName(GeneralInfoConstants.WORK, "MyWork");
    }

    @Override
    public String getContact() {
        return getElementByName(GeneralInfoConstants.CONTACT, "Contact Us");
    }

    @Override
    public String getFollows() {
        return getElementByName(GeneralInfoConstants.FOLLOW, "Follow Us");
    }

    @Override
    public String getProjects() {
        return getElementByName(GeneralInfoConstants.PROJECTS, "Our Projects");
    }

    @Override
    public String getSkills() {
        return getElementByName(GeneralInfoConstants.SKILLS, "Value that are not provided");
    }

    @Override
    public String getRead() {
        return getElementByName(GeneralInfoConstants.READ, "Read More");
    }

    @Override
    public String getExpandPicture() {
        return getElementByName(GeneralInfoConstants.EXPAND_PICTURE, "images/final/expand-img.jpg");
    }

    @Override
    public String getExpandHeader() {
        return getElementByName(GeneralInfoConstants.EXPAND_HEADER, "Expand Header. Please fill the section in config");
    }

    @Override
    public String getExpandText() {
        return getElementByName(GeneralInfoConstants.EXPAND_TEXT, "Expand Text. Please fill the section in config");
    }

    protected String getElementByName(String name, String defaultValue) {
        return generalValueDAO.getGeneralValues().stream().filter(generalValue -> generalValue.getInternalName()
                        .toUpperCase(Locale.ROOT).equals(name))
                .findFirst().orElseGet(() -> GeneralValue.builder().externalName(defaultValue).build())
                .getExternalName();
    }

}
