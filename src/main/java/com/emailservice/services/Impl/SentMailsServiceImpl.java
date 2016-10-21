package com.emailservice.services.Impl;

import com.emailservice.dao.MailQueueDao;
import com.emailservice.dao.SentMailsDao;
import com.emailservice.models.SentMails;
import com.emailservice.services.SentMailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by murad on 10/18/16.
 */
@Service("sentMailsService")
@Transactional
public class SentMailsServiceImpl implements SentMailsService {

    private static final Logger logger = LoggerFactory.getLogger(SentMailsServiceImpl.class);

    @Autowired
    private SentMailsDao sentMailsDao;

    public List<SentMails> getMails(Integer page, Integer limit) {
        return this.sentMailsDao.getMails(page,limit);
    }

    public List<SentMails> getMails() {
        return this.sentMailsDao.getMails();
    }

    public List<SentMails> getMailsByDate(String date, Integer page, Integer limit) {
        return this.sentMailsDao.getMailsByDate(date,page,limit);
    }

    public void addMail(SentMails mails) {
        this.sentMailsDao.addMail(mails);
    }

    public void addMail(List<SentMails> mails) {
        this.sentMailsDao.addMail(mails);
    }

    public Integer countMails() {
        return this.sentMailsDao.countMails();
    }

    public Integer countMailsbyDate(String date) {
        return this.sentMailsDao.countMailsbyDate(date);
    }

    public void deleteMail(Integer id) {
        logger.info("delete id: {}",id);
        this.sentMailsDao.deleteMail(id);
    }
}
