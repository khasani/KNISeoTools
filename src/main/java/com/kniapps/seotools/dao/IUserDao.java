package com.kniapps.seotools.dao;

import com.kniapps.seotools.model.User;

public interface IUserDao {
 
    User findByUserName(String username);
 
}