package com.axmor.util;

import com.google.common.html.HtmlEscapers;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

import static com.axmor.util.RequestUtil.getSessionCurrentUsername;

public class ViewUtil {

    public static String render(Request request, Map<String, Object> model, String templatePath) {
        model.put("currentUsername", getSessionCurrentUsername(request));
        model.put("web", Path.Web.class);
        model.put("escaper", HtmlEscapers.htmlEscaper());
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
