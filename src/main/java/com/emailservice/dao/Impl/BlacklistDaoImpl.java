package com.emailservice.dao.Impl;

import com.emailservice.dao.BlacklistDao;
import com.emailservice.dao.GroupsDao;
import com.emailservice.models.Blacklist;
import com.emailservice.models.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
@Repository("blacklistDao")
public class BlacklistDaoImpl implements BlacklistDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(BlacklistDaoImpl.class);

    public List<Blacklist> getMails(Integer page, Integer limit) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Blacklist> mailList = session
                .createQuery("from Blacklist")
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return mailList;
    }

    public List<Blacklist> getMails() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Blacklist> mailList = session
                .createQuery("from Blacklist")
                .list();

        return mailList;
    }

    public void addMail(Blacklist mails) {
        this.sessionFactory.getCurrentSession().persist(mails);
    }

    @SuppressWarnings("unchecked")
    public void addMail(List<Blacklist> mails) {
        ArrayList<String > insertedValues = new ArrayList<String>();
        for (Blacklist mail : mails){
            insertedValues.add("('"+mail.getMail()+"')");
        }

        this.sessionFactory.getCurrentSession()
                .createNativeQuery("INSERT INTO blacklist (`mail`)" +
                        " VALUES "+String.join(",",insertedValues))
                .executeUpdate();
    }

    public Integer countMails(){
        return ((Long) sessionFactory.getCurrentSession().createQuery("SELECT count(id) from Blacklist").uniqueResult()).intValue();
    }

    public void deleteMail(Integer id){
        Blacklist mails = new Blacklist();
        mails.setId(id);
        sessionFactory.getCurrentSession().delete(mails);
    }
}
