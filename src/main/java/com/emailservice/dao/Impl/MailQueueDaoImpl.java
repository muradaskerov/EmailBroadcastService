package com.emailservice.dao.Impl;

import com.emailservice.dao.MailQueueDao;
import com.emailservice.models.MailQueue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/15/16.
 */
@Repository("mailqueueDao")
public class MailQueueDaoImpl implements MailQueueDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(MailQueueDaoImpl.class);

    public List<MailQueue> getMails(Integer page, Integer limit) {
        Session session = this.sessionFactory.getCurrentSession();
        List<MailQueue> mailList = session
                .createQuery("from MailQueue")
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return mailList;
    }

    public List<MailQueue> getMails() {
        Session session = this.sessionFactory.getCurrentSession();
        List<MailQueue> mailList = session
                .createQuery("from MailQueue")
                .list();

        return mailList;
    }

    @SuppressWarnings("unchecked")
    public void addMail(List<MailQueue> mails) {
        ArrayList<String > insertedValues = new ArrayList<String>();
        for (MailQueue mailQueue : mails){
            insertedValues.add("('"+mailQueue.getFrom()+"','"+mailQueue.getTo()+"','"+mailQueue.getSubject()+"','"+mailQueue.getContent()+"','"+mailQueue.getSendDate()+"','"+mailQueue.getInserted()+"','"+mailQueue.getStatus()+"')");
        }

        this.sessionFactory.getCurrentSession()
                .createNativeQuery("INSERT INTO mailqueue (`from`,`to`,`subject`,content,sendDate,inserted,`status`)" +
                        " VALUES "+String.join(",",insertedValues))
                .executeUpdate();
    }

    public void addMail(MailQueue mails) {
        this.sessionFactory.getCurrentSession().persist(mails);
    }

    public Integer countMails() {
        return ((Long) sessionFactory.getCurrentSession().createQuery("SELECT count(id) from MailQueue").uniqueResult()).intValue();
    }
    public Integer countMailsbyDate(String date){
        return ((Long) sessionFactory.getCurrentSession()
                .createQuery("SELECT count(id) from MailQueue where sendDate = :date ")
                .setParameter("date",date)
                .uniqueResult())
                .intValue();
    }

    public void deleteMail(Integer id) {
        MailQueue mails = new MailQueue();
        mails.setId(id);
        sessionFactory.getCurrentSession().delete(mails);
    }
    public List<MailQueue> getMailsByDate(String date,Integer page,Integer limit){
        Session session = this.sessionFactory.getCurrentSession();
        List<MailQueue> mailList = session
                .createQuery("from MailQueue where sendDate=?")
                .setParameter(0,date)
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return mailList;
    }
}
