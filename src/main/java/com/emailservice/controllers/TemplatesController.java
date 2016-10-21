package com.emailservice.controllers;

import com.emailservice.models.Groups;
import com.emailservice.models.MailTemplate;
import com.emailservice.services.GroupsService;
import com.emailservice.services.TemplatesService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by murad on 10/8/16.
 */

@Controller
public class TemplatesController {

    @Autowired
    TemplatesService templatesService;

    private static final Logger logger = LoggerFactory.getLogger(TemplatesController.class);

    @RequestMapping(value = "/templates/list",method = RequestMethod.GET)
    public String mails(Model model, @RequestParam(value = "page", required = false) Integer pageParam){
        int limit = 10;
        int page = (pageParam != null ) ? pageParam : 1;
        int countTemplates = templatesService.countTemplates();
        model.addAttribute("templates",templatesService.getTemplates(page,limit));
        model.addAttribute("countPages",Math.ceil((double) countTemplates/limit));

        return "mailtemplates/list";
    }

    @RequestMapping(value = "/templates/add",method = RequestMethod.GET)
    public String templatesAdd(){

        return "mailtemplates/add";
    }

    @RequestMapping(value = "/templates/add",method = RequestMethod.POST)
    public String templatesAddPost(@ModelAttribute MailTemplate mailTemplate){
        templatesService.addTemplate(mailTemplate);

        return "redirect:/templates/list";
    }

    @RequestMapping(value = "/templates/delete",method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id){
        templatesService.deleteTemplate(id);

        return "redirect:/templates/list";
    }


    @RequestMapping(value = "/ajax/templates",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String ajaxGetByID(@RequestParam(value = "id") Integer id){
        List<MailTemplate> templateById = templatesService.getTemplatesbyId(id);
        if(templateById.size() > 0){
            MailTemplate templt = templateById.get(0);

            ObjectMapper objectMapper = new ObjectMapper();
            try{
                return objectMapper.writeValueAsString(templt);
            }catch (JsonProcessingException jpe){
                logger.error("Message: "+jpe.getMessage());
            }



        }
        return "null";
    }
}
