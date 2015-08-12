package com.kniapps.seotools.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kniapps.seotools.model.User;
 
 
public class UserDao implements IUserDao {
 
    private SessionFactory sessionFactory;
 
    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {
 
        List<User> users = new ArrayList<User>();
 
        users = getSessionFactory().getCurrentSession()
            .createQuery("from User where username=?")
            .setParameter(0, username).list();
 
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
 
    }
 
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
}
