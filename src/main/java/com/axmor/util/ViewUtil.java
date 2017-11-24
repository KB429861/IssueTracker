package com.axmor.util;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public class ViewUtil {

    public static String render(Map model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
