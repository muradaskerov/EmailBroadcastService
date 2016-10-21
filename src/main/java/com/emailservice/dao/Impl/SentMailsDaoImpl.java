package com.emailservice.dao.Impl;

import com.emailservice.dao.SentMailsDao;
import com.emailservice.models.SentMails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/18/16.
 */
@Repository("sentMailsDao")
public class SentMailsDaoImpl implements SentMailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(SentMailsDaoImpl.class);

    public List<SentMails> getMails(Integer page, Integer limit) {
        Session session = this.sessionFactory.getCurrentSession();
        List<SentMails> mailList = session
                .createQuery("from SentMails")
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return mailList;
    }

    public List<SentMails> getMails() {
        Session session = this.sessionFactory.getCurrentSession();
        List<SentMails> mailList = session
                .createQuery("from SentMails")
                .list();

        return mailList;
    }

    public List<SentMails> getMailsByDate(String date, Integer page, Integer limit) {
        Session session = this.sessionFactory.getCurrentSession();
        List<SentMails> mailList = session
                .createQuery("from SentMails where sentDate=?")
                .setParameter(0,date)
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return mailList;
    }

    public void addMail(SentMails mails) {
        this.sessionFactory.getCurrentSession().persist(mails);
    }

    @SuppressWarnings("unchecked")
    public void addMail(List<SentMails> mails) {
        ArrayList<String > insertedValues = new ArrayList<String>();
        for (SentMails mail : mails){
            insertedValues.add("('"+mail.getFrom()+"','"+mail.getTo()+"','"+mail.getSubject()+"','"+mail.getContent()+"','"+mail.getSentDate()+"','"+mail.getInserted()+"')");
        }

        this.sessionFactory.getCurrentSession()
                .createNativeQuery("INSERT INTO sentmails (`from`,`to`,`subject`,content,sentDate,inserted)" +
                        " VALUES "+String.join(",",insertedValues))
                .executeUpdate();
    }

    public Integer countMails() {
        return ((Long) sessionFactory.getCurrentSession().createQuery("SELECT count(id) from SentMails").uniqueResult()).intValue();
    }

    public Integer countMailsbyDate(String date) {
        return ((Long) sessionFactory.getCurrentSession()
                .createQuery("SELECT count(id) from SentMails where sentDate = :date ")
                .setParameter("date",date)
                .uniqueResult())
                .intValue();
    }

    public void deleteMail(Integer id) {
        SentMails mails = new SentMails();
        mails.setId(id);
        sessionFactory.getCurrentSession().delete(mails);
    }
}
