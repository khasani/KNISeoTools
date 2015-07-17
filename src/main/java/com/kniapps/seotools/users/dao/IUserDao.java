package com.kniapps.seotools.users.dao;

import com.kniapps.seotools.users.model.User;

public interface IUserDao {
 
    User findByUserName(String username);
 
}