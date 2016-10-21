package com.emailservice.services.Impl;

import com.emailservice.dao.GroupsDao;
import com.emailservice.dao.TemplatesDao;
import com.emailservice.models.Groups;
import com.emailservice.models.MailTemplate;
import com.emailservice.services.GroupsService;
import com.emailservice.services.TemplatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
@Service("templatesService")
@Transactional
public class TemplatesServiceImpl implements TemplatesService {
    private static final Logger logger = LoggerFactory.getLogger(TemplatesServiceImpl.class);

    @Autowired
    private TemplatesDao templatesDao;

    public List<MailTemplate> getTemplates(Integer page, Integer limit){
        return this.templatesDao.getTemplates(page, limit);
    };

    public List<MailTemplate> getTemplates(){
        return this.templatesDao.getTemplates();
    };

    public void addTemplate(MailTemplate mailTemplate) {
        this.templatesDao.addTemplate(mailTemplate);
    }

    public List<MailTemplate> getTemplatesbyId(Integer id){
        return this.templatesDao.getTemplatesbyId(id);
    }

    public void deleteTemplate(Integer id){
        this.templatesDao.deleteTemplate(id);
    }

    public Integer countTemplates(){
        return this.templatesDao.countTemplates();
    }
}
