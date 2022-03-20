package com.yk.protfolio.springportfolio.services;

import org.springframework.stereotype.Service;

/**
 * Service to control text from back-end side
 */
@Service
public class GenericValuesServiceImp implements GenericValuesService {

    @Override
    public String getPortfolio() {
        return "Portfolio";
    }

    @Override
    public String getHome() {
        return "Home";
    }

    @Override
    public String getAbout() {
        return "About";
    }

    @Override
    public String getWork() {
        return "MyWork";
    }

    @Override
    public String getContact() {
        return "Contact Us";
    }

    @Override
    public String getFollows() {
        return "Follow Us";
    }

    @Override
    public String getProjects() {
        return "Our Projects";
    }

    public String getSkills() {
        return "Abap/Java Developer";
    }

    public String getRead() {
        return "Read More";
    }

}
