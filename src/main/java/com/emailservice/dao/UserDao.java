package com.emailservice.dao;

import com.emailservice.models.User;

import java.util.List;

/**
 * Created by murad on 10/1/16.
 */
public interface UserDao {
    User findByUserName(String username);
}
