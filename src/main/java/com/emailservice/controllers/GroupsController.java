package com.emailservice.controllers;

import com.emailservice.models.Groups;
import com.emailservice.services.GroupsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by murad on 10/8/16.
 */
@Controller
public class GroupsController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    GroupsService groupsService;

    @RequestMapping(value = "/groups/list",method = RequestMethod.GET)
    public String mails(Model model, @RequestParam(value = "page", required = false) Integer pageParam){
        int limit = 10;
        int page = (pageParam != null ) ? pageParam : 1;
        int countGroups = groupsService.countGroups();
        model.addAttribute("groups",groupsService.getGroups(page,limit));
        model.addAttribute("countGroups",countGroups);
        model.addAttribute("countPages",Math.ceil((double) countGroups/limit));

        return "groups/list";
    }

    @RequestMapping(value = "/groups/add",method = RequestMethod.GET)
    public String mailsAdd(Model model){
        model.addAttribute(new Groups());
        return "groups/add";
    }
    @RequestMapping(value = "/groups/add",method = RequestMethod.POST)
    public String mailsAddPost(@ModelAttribute Groups groups){
        groupsService.addGroup(groups);

        return "redirect:/groups/list";
    }

    @RequestMapping(value = "/groups/delete",method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id){
        groupsService.deleteGroup(id);

        return "redirect:/groups/list";
    }
}
