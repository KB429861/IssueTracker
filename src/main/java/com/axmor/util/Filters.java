package com.axmor.util;

import spark.Filter;

public class Filters {

    /**
     * Add a trailing slash to path if it doesn't exist.
     */
    public static Filter addTrailingSlashes = (request, response) -> {
        if (!request.pathInfo().endsWith("/")) {
            response.redirect(request.pathInfo() + "/");
        }
    };
}
