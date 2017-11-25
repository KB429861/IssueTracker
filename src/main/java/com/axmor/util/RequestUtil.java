package com.axmor.util;

import spark.Request;

public class RequestUtil {

    public static String getSessionCurrentUsername(Request request) {
        return request.session().attribute("currentUsername");
    }

    public static void setSessionCurrentUsername(Request request, String username) {
        request.session().attribute("currentUsername", username);
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
        return request.queryParams("username");
    }

    public static String getQueryPassword(Request request) {
        return request.queryParams("password");
    }

    public static String getQuerySummary(Request request) {
        return request.queryParams("summary");
    }

    public static String getQueryDescription(Request request) {
        return request.queryParams("description");
    }

    public static String getQueryText(Request request) {
        return request.queryParams("text");
    }

    public static String getQueryRedirect(Request request) {
        return request.queryParams("redirect");
    }

    public static int getParamId(Request request) {
        return Integer.parseInt(request.params(":id"));
    }
}
