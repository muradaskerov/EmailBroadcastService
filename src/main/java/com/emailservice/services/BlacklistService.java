package com.emailservice.services;

import com.emailservice.models.Blacklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
public interface BlacklistService {
    public List<Blacklist> getMails(Integer page, Integer limit);
    public List<Blacklist> getMails();
    public void addMail(Blacklist mails);
    public void addMail(List<Blacklist> mails);
    public Integer countMails();
    public void deleteMail(Integer id);


}
