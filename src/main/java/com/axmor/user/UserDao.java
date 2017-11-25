package com.axmor.user;

public interface UserDao {

    User getUser(String username);

    boolean insertUser(User user);
}
