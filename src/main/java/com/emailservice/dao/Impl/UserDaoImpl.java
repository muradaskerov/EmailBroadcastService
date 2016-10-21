package com.emailservice.dao.Impl;

import com.emailservice.dao.UserDao;
import com.emailservice.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/1/16.
 */

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        List<User> users = new ArrayList<User>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();

        if (users.size() > 0) {
            logger.info("User var");
            return users.get(0);
        } else {
            logger.info("User yoxdu");
            return null;
        }

    }
}
