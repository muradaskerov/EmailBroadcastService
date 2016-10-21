package com.emailservice.services.Impl;

import com.emailservice.dao.GroupsDao;
import com.emailservice.models.Groups;
import com.emailservice.services.GroupsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
@Service("groupsService")
@Transactional
public class GroupsServiceImpl implements GroupsService {
    private static final Logger logger = LoggerFactory.getLogger(GroupsServiceImpl.class);

    @Autowired
    private GroupsDao groupDao;

    public List<Groups> getGroups(Integer page, Integer limit){
        return this.groupDao.getGroups(page,limit);
    };

    public List<Groups> getGroups(){
        return this.groupDao.getGroups();
    };

    public void addGroup(Groups groups) {
        this.groupDao.addGroup(groups);
    }

    public void deleteGroup(Integer id){
        this.groupDao.deleteGroup(id);
    }

    public Integer countGroups(){
        return this.groupDao.countGroups();
    }
}
