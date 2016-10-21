package com.emailservice.dao.Impl;

import com.emailservice.dao.MailsDao;
import com.emailservice.models.Groups;
import com.emailservice.models.Mails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/5/16.
 */

@Repository("mailsDao")
public class MailsDaoImpl implements MailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(MailsDaoImpl.class);

    public List<Mails> getMails(Integer page, Integer limit) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Mails> mailList = session
                .createQuery("from Mails")
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return mailList;
    }

    public void addMail(Mails mails) {
        this.sessionFactory.getCurrentSession().persist(mails);
    }

    @SuppressWarnings("unchecked")
    public void addMail(List<Mails> mails) {
        ArrayList<String > insertedValues = new ArrayList<String>();
        for (Mails mail : mails){
            insertedValues.add("('"+mail.getMail()+"','"+mail.getGroup()+"')");
        }

        this.sessionFactory.getCurrentSession()
                .createNativeQuery("INSERT INTO mails (`mail`,`group`)" +
                        " VALUES "+String.join(",",insertedValues))
                .executeUpdate();
    }

    public Integer countMails(){
        return ((Long) sessionFactory.getCurrentSession().createQuery("SELECT count(id) from Mails").uniqueResult()).intValue();
    }

    public void deleteMail(Integer id){
        Mails mails = new Mails();
        mails.setId(id);
        sessionFactory.getCurrentSession().delete(mails);
    }

    @SuppressWarnings("unchecked")
    public List<Mails> getMailsbyGroup(Integer group, Integer page, Integer limit){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session
                .createQuery("from Mails where group= ?")
                .setParameter(0,group)
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit);
        List<Mails> mailList = query.list();
        for(Mails p : mailList){
            logger.info("User List::"+p.getMail());
        }
        return mailList;
    };
}
