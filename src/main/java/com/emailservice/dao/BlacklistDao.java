package com.emailservice.dao;

import com.emailservice.models.Blacklist;
import com.emailservice.models.Groups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
public interface BlacklistDao {
    public List<Blacklist> getMails(Integer page, Integer limit);
    public List<Blacklist> getMails();
    public void addMail(Blacklist mails);
    public void addMail(List<Blacklist> mails);
    public Integer countMails();
    public void deleteMail(Integer id);


}
