package com.emailservice.services.Impl;

import com.emailservice.dao.BlacklistDao;
import com.emailservice.dao.GroupsDao;
import com.emailservice.models.Blacklist;
import com.emailservice.models.Groups;
import com.emailservice.services.BlacklistService;
import com.emailservice.services.GroupsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
@Service("blacklistService")
@Transactional
public class BlacklistServiceImpl implements BlacklistService {
    private static final Logger logger = LoggerFactory.getLogger(BlacklistServiceImpl.class);

    @Autowired
    private BlacklistDao blacklistDao;

    public List<Blacklist> getMails(Integer page, Integer limit){
        return this.blacklistDao.getMails(page,limit);
    };
    public List<Blacklist> getMails(){
        return this.blacklistDao.getMails();
    };


    public void addMail(Blacklist mails) {
        this.blacklistDao.addMail(mails);
    }

    public void addMail(List<Blacklist> mails){
        this.blacklistDao.addMail(mails);
    }
    public Integer countMails(){
        return this.blacklistDao.countMails();
    }

    public void deleteMail(Integer id){
        this.blacklistDao.deleteMail(id);
    }
}
