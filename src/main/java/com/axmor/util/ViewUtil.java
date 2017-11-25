package com.axmor.util;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public class ViewUtil {

    public static String render(Map<String, Object> model, String templatePath) {
        model.put("WebPath", Path.Web.class);
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
