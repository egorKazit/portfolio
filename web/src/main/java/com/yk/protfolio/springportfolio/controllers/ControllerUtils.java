package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.components.GenericValuesWrapper;
import com.yk.protfolio.springportfolio.components.SocialWrapper;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.HtmlConstants;
import com.yk.protfolio.springportfolio.utilities.Page;
import org.springframework.ui.Model;

import java.util.stream.Stream;

/**
 * controller utils
 */
public final class ControllerUtils {

    static final String ACTIVE_MARKER = "_active";

    private ControllerUtils() {
    }

    /**
     * gets page.
     * The method receive a page and fills the general part of model
     *
     * @param requestedPage requested page
     * @param model         model
     * @return page
     */
    static String getPage(Page requestedPage, Model model) {
        Stream.of(Page.values()).forEach(page ->
                model.addAttribute(page.getName() + ACTIVE_MARKER, HtmlConstants.NAVIGATION_ITEM_INACTIVE));
        model.addAttribute(requestedPage.getName() + ControllerUtils.ACTIVE_MARKER, HtmlConstants.NAVIGATION_ITEM_ACTIVE);
        model.addAttribute(Attributes.GENERAL, GenericValuesWrapper.getGenericValuesService());
        model.addAttribute(Attributes.SOCIALS, SocialWrapper.getSocialService().getSocials());
        return requestedPage.getName();
    }

}
