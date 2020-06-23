package com.charlie.ocr.controllers.controllers;

import spark.ModelAndView;
import spark.Route;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Webapp controller class.
 */
public class WebappController {

    public WebappController() {
    }

    public Route about = (request, response) -> render("about.mustache");

    /*
     * Utilities
     */
    public static String render(Map<String, Object> model, String templatePath) {
        return new MustacheTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String render(String templatePath) {
        Map<String, Object> map = new HashMap<>();
        return render(map, templatePath);
    }

}
