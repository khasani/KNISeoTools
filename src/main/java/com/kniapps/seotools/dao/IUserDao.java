package com.kniapps.seotools.dao;


import com.kniapps.seotools.model.User;

public interface IUserDao  extends GenericDao<User, Long> {
 
    User findByUserName(String username);
 
}