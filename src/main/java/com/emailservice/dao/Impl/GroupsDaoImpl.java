package com.emailservice.dao.Impl;

import com.emailservice.dao.GroupsDao;
import com.emailservice.models.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
@Repository("groupDao")
public class GroupsDaoImpl implements GroupsDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(GroupsDaoImpl.class);

    public List<Groups> getGroups(Integer page, Integer limit) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Groups> groupList = session
                .createQuery("from Groups")
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return groupList;
    }

    public List<Groups> getGroups() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Groups> groupList = session
                .createQuery("from Groups")
                .list();

        return groupList;
    }

    public void addGroup(Groups groups) {
        this.sessionFactory.getCurrentSession().persist(groups);
    }

    public void deleteGroup(Integer id){
        Groups mails = new Groups();
        mails.setId(id);
        sessionFactory.getCurrentSession().delete(mails);
    }

    public Integer countGroups(){
        return ((Long) sessionFactory.getCurrentSession().createQuery("SELECT count(id) from Groups").uniqueResult()).intValue();
    }
}
