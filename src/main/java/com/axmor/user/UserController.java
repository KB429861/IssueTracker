package com.axmor.user;

import static com.axmor.Application.userDao;

public class UserController {

    public static boolean authenticate(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        User user = userDao.getUser(username);
        return user != null && password.equals(user.getPassword());
    }

    public static boolean isUsernameAvailable(String username) {
        User user = userDao.getUser(username);
        return user == null;
    }

    public static void register(String username, String password) {
        User user = new User(username, password);
        userDao.insertUser(user);
    }
}
