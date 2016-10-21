package com.emailservice.dao.Impl;

import com.emailservice.dao.ConfigDao;
import com.emailservice.models.Config;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by murad on 10/19/16.
 */
@Repository("configDao")
public class ConfigDaoImpl implements ConfigDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(ConfigDaoImpl.class);

    public List<Config> getConfig() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Config> config = session
                .createQuery("from Config")
                .list();

        return config;
    }

    public void updateConfig(Config config) {
        this.sessionFactory.getCurrentSession().update(config);
    }

}
