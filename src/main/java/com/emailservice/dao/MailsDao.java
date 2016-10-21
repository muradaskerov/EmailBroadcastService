package com.emailservice.dao;

import com.emailservice.models.MailQueue;
import com.emailservice.models.Mails;

import java.util.List;

/**
 * Created by murad on 10/5/16.
 */
public interface MailsDao {

    public List<Mails> getMails(Integer page, Integer limit);
    public void addMail(Mails mails);
    public void addMail(List<Mails> mails);
    public Integer countMails();
    public void deleteMail(Integer id);
    public List<Mails> getMailsbyGroup(Integer group, Integer page, Integer limit);

}
