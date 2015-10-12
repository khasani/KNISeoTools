package com.kniapps.seotools.dao;

import java.util.ArrayList;
import java.util.List;

import com.kniapps.seotools.model.User;
 
 
public class UserDao extends HibernateDao<User, Long> implements IUserDao {
  
    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {
 
        List<User> users = new ArrayList<User>();
 
        users = currentSession()
            .createQuery("from User where username=?")
            .setParameter(0, username).list();
 
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
 
    }
 
}
