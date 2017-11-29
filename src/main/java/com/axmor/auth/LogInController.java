package com.axmor.auth;

import com.axmor.user.UserController;
import com.axmor.util.Path;
import com.axmor.util.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static com.axmor.util.RequestUtil.*;

public class LogInController {

    public static Route getLogIn = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put(Path.Model.REDIRECT, removeSessionRedirect(request));
        return ViewUtil.render(request, model, Path.Template.LOG_IN);
    };

    public static Route postLogIn = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        String username = getQueryUsername(request);
        String password = getQueryPassword(request);
        if (!UserController.authenticate(username, password)) {
            model.put(Path.Model.FAILED, true);
            return ViewUtil.render(request, model, Path.Template.LOG_IN);
        }
        setSessionCurrentUsername(request, username);
        String route = getQueryRedirect(request);
        if (route != null) {
            response.redirect(route);
        } else {
            response.redirect(Path.Web.ISSUES);
        }
        return null;
    };

    public static Route postLogOut = (request, response) -> {
        removeSessionUsername(request);
        response.redirect(Path.Web.LOG_IN);
        return null;
    };

    /**
     * Checks whether the user is logged in and redirects to the login page, if it doesn't.
     *
     * @param request  Request object.
     * @param response Response object.
     * @return The parameter that determines whether the user is logged in.
     */
    public static boolean ensureUserIsLoggedIn(Request request, Response response) {
        if (getSessionCurrentUsername(request) == null) {
            setSessionRedirect(request, request.pathInfo());
            response.redirect(Path.Web.LOG_IN);
            return false;
        }
        return true;
    }
}
