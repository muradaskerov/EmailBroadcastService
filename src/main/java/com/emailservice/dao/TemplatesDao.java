package com.emailservice.dao;

        import com.emailservice.models.Groups;
        import com.emailservice.models.MailTemplate;

        import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
public interface TemplatesDao {
    public List<MailTemplate> getTemplates(Integer page, Integer limit);
    public List<MailTemplate> getTemplates();
    public void addTemplate(MailTemplate mailTemplate);
    public List<MailTemplate> getTemplatesbyId(Integer id);
    public void deleteTemplate(Integer id);
    public Integer countTemplates();
}
