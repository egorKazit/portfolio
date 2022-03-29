package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.components.GenericValuesWrapper;
import com.yk.protfolio.springportfolio.utilities.HtmlConstants;
import com.yk.protfolio.springportfolio.utilities.Page;
import java.util.stream.Stream;
import org.springframework.ui.Model;

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
        model.addAttribute("general", GenericValuesWrapper.getGenericValuesService());
        return requestedPage.getName();
    }

}
