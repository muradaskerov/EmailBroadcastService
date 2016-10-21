package com.emailservice.services.Impl;

import com.emailservice.dao.MailsDao;
import com.emailservice.models.Groups;
import com.emailservice.models.Mails;
import com.emailservice.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by murad on 10/2/16.
 */
@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private MailsDao mailsDao;

    public List<Mails> getMails(Integer page, Integer limit){
        return this.mailsDao.getMails(page,limit);
    };

    public void addMail(Mails mails) {
        this.mailsDao.addMail(mails);
    }

    public void addMail(List<Mails> mails){
        this.mailsDao.addMail(mails);
    }

    public Integer countMails(){
        return this.mailsDao.countMails();
    }

    public void deleteMail(Integer id){
        this.mailsDao.deleteMail(id);
    }

    public List<Mails> getMailsbyGroup(Integer group, Integer page, Integer limit){
        return this.mailsDao.getMailsbyGroup(group,page,limit);
    }
}
