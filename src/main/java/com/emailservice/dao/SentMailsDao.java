package com.emailservice.dao;

import com.emailservice.models.SentMails;

import java.util.List;

/**
 * Created by murad on 10/18/16.
 */
public interface SentMailsDao {
    public List<SentMails> getMails(Integer page, Integer limit);
    public List<SentMails> getMails();
    public List<SentMails> getMailsByDate(String date,Integer page,Integer limit);
    public void addMail(SentMails mails);
    public void addMail(List<SentMails> mails);
    public Integer countMails();
    public Integer countMailsbyDate(String date);
    public void deleteMail(Integer id);
}
