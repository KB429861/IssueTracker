package com.axmor.auth;

import com.axmor.user.UserController;
import com.axmor.util.Path;
import com.axmor.util.ViewUtil;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static com.axmor.util.RequestUtil.*;

public class SignUpController {

    public static Route getSignUp = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put(Path.Model.REDIRECT, removeSessionRedirect(request));
        return ViewUtil.render(request, model, Path.Template.SIGN_UP);
    };

    public static Route postSignUp = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        String username = getQueryUsername(request);
        String password = getQueryPassword(request);
        if (!UserController.isUsernameAvailable(username)) {
            model.put(Path.Model.FAILED, true);
            return ViewUtil.render(request, model, Path.Template.SIGN_UP);
        }
        UserController.register(username, password);
        model.put(Path.Model.SUCCEEDED, true);
        setSessionCurrentUsername(request, username);
        String route = getQueryRedirect(request);
        if (route != null) {
            response.redirect(route);
        }
        return ViewUtil.render(request, model, Path.Template.SIGN_UP);
    };
}
