package com.emailservice.services;

import com.emailservice.models.Groups;
import com.emailservice.models.Mails;

import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
public interface GroupsService {
    public List<Groups> getGroups(Integer page, Integer limit);
    public List<Groups> getGroups();
    public void addGroup(Groups groups);
    public void deleteGroup(Integer id);
    public Integer countGroups();
}
