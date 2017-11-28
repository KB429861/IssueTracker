package com.axmor.util;

import spark.Request;

import static com.google.common.html.HtmlEscapers.htmlEscaper;

public class RequestUtil {

    public static String getSessionCurrentUsername(Request request) {
        return request.session().attribute("currentUsername");
    }

    public static void setSessionCurrentUsername(Request request, String username) {
        request.session().attribute("currentUsername", username);
    }

    public static void removeSessionUsername(Request request) {
        request.session().removeAttribute("currentUsername");
    }

    public static void setSessionRedirect(Request request, String route) {
        request.session().attribute("redirect", route);
    }

    public static String removeSessionRedirect(Request request) {
        String route = request.session().attribute("redirect");
        request.session().removeAttribute("redirect");
        return route;
    }

    public static String getQueryUsername(Request request) {
        return htmlEscaper().escape(request.queryParams("username"));
    }

    public static String getQueryPassword(Request request) {
        return htmlEscaper().escape(request.queryParams("password"));
    }

    public static String getQuerySummary(Request request) {
        return htmlEscaper().escape(request.queryParams("summary"));
    }

    public static String getQueryDescription(Request request) {
        return htmlEscaper().escape(request.queryParams("description"));
    }

    public static String getQueryText(Request request) {
        return htmlEscaper().escape(request.queryParams("text"));
    }

    public static String getQueryStatus(Request request) {
        return htmlEscaper().escape(request.queryParams("status"));
    }

    public static String getQueryRedirect(Request request) {
        return htmlEscaper().escape(request.queryParams("redirect"));
    }

    public static int getParamId(Request request) {
        return Integer.parseInt(request.params(":id"));
    }
}
