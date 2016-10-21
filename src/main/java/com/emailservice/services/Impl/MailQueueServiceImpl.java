package com.emailservice.services.Impl;

import com.emailservice.dao.MailQueueDao;
import com.emailservice.models.MailQueue;
import com.emailservice.services.MailQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by murad on 10/15/16.
 */
@Service("mailqueueService")
@Transactional
public class MailQueueServiceImpl implements MailQueueService {

    private static final Logger logger = LoggerFactory.getLogger(MailQueueServiceImpl.class);

    @Autowired
    private MailQueueDao mailQueueDao;

    public List<MailQueue> getMails(Integer page, Integer limit) {
        return this.mailQueueDao.getMails(page,limit);
    }

    public List<MailQueue> getMails() {
        return this.mailQueueDao.getMails();
    }

    public List<MailQueue> getMailsByDate(String date,Integer page,Integer limit){
        return this.mailQueueDao.getMailsByDate(date,page,limit);
    }

    public void addMail(MailQueue mails) {
        this.mailQueueDao.addMail(mails);
    }

    public void addMail(List<MailQueue> mails){ this.mailQueueDao.addMail(mails);}

    public Integer countMails() {
        return this.mailQueueDao.countMails();
    }

    public Integer countMailsbyDate(String date){
        return this.mailQueueDao.countMailsbyDate(date);
    }

    public void deleteMail(Integer id) {
        logger.info("delete id: {}",id);
        this.mailQueueDao.deleteMail(id);
    }
}
