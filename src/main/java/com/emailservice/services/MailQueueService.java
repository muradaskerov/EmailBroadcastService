package com.emailservice.services;

import com.emailservice.models.MailQueue;

import java.util.List;

/**
 * Created by murad on 10/15/16.
 */
public interface MailQueueService {
    public List<MailQueue> getMails(Integer page, Integer limit);
    public List<MailQueue> getMails();
    public List<MailQueue> getMailsByDate(String date,Integer page,Integer limit);
    public void addMail(MailQueue mails);
    public void addMail(List<MailQueue> mails);
    public Integer countMails();
    public Integer countMailsbyDate(String date);
    public void deleteMail(Integer id);
}
