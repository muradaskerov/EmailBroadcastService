package com.emailservice.dao.Impl;

import com.emailservice.dao.GroupsDao;
import com.emailservice.dao.TemplatesDao;
import com.emailservice.models.Groups;
import com.emailservice.models.MailTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
@Repository("mailTemplatesDao")
public class TemplatesDaoImpl implements TemplatesDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(TemplatesDaoImpl.class);

    public List<MailTemplate> getTemplates(Integer page, Integer limit) {
        Session session = this.sessionFactory.getCurrentSession();
        List<MailTemplate> templateList = session.createQuery("from MailTemplate")
                .setFirstResult(((page-1)*limit))
                .setMaxResults(limit)
                .list();

        return templateList;
    }

    public List<MailTemplate> getTemplates() {
        Session session = this.sessionFactory.getCurrentSession();
        List<MailTemplate> templateList = session
                .createQuery("from MailTemplate")
                .list();

        return templateList;
    }

    public void addTemplate(MailTemplate mailTemplate){
        this.sessionFactory.getCurrentSession().persist(mailTemplate);
    };

    @SuppressWarnings("unchecked")
    public List<MailTemplate> getTemplatesbyId(Integer id){
        Session session = this.sessionFactory.getCurrentSession();
        List<MailTemplate> mailList = session
                .createQuery("from MailTemplate where id= ?")
                .setParameter(0,id).list();

        return mailList;
    }

    public void deleteTemplate(Integer id){
        MailTemplate mails = new MailTemplate();
        mails.setId(id);
        sessionFactory.getCurrentSession().delete(mails);
    }

    public Integer countTemplates(){
        return ((Long) sessionFactory.getCurrentSession().createQuery("SELECT count(id) from MailTemplate").uniqueResult()).intValue();
    }
}
