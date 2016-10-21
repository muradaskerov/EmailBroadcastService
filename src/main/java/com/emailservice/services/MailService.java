package com.emailservice.services;

import com.emailservice.models.Groups;
import com.emailservice.models.Mails;

import java.util.List;

/**
 * Created by murad on 10/2/16.
 */
public interface MailService {
    public List<Mails> getMails(Integer page, Integer limit);
    public void addMail(Mails mails);
    public void addMail(List<Mails> mails);
    public Integer countMails();
    public void deleteMail(Integer id);
    public List<Mails> getMailsbyGroup(Integer group, Integer page, Integer limit);
}
