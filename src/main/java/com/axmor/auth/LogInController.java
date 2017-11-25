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
        model.put(Path.Model.SUCCEEDED, true);
        String route = getQueryRedirect(request);
        if (route != null) {
            response.redirect(route);
        }
        return ViewUtil.render(request, model, Path.Template.LOG_IN);
    };

    public static void ensureUserIsLoggedIn(Request request, Response response) {
        if (getSessionCurrentUsername(request) == null) {
            setSessionRedirect(request, request.pathInfo());
            response.redirect(Path.Web.LOG_IN);
        }
    }
}
