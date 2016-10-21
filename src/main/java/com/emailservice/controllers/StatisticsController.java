package com.emailservice.controllers;

import com.emailservice.helpers.CommonHelper;
import com.emailservice.services.MailQueueService;
import com.emailservice.services.SentMailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by murad on 10/18/16.
 */
@Controller
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    MailQueueService mailQueueService;

    @Autowired
    SentMailsService sentMailsService;

    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){

        String date = CommonHelper.getCurrentDate("yyyy-MM-dd");

        if(request.getParameter("date") != null){
            date = request.getParameter("date");
        }

        model.addAttribute("date", date);
        model.addAttribute("sentmails",sentMailsService.countMailsbyDate(date));
        model.addAttribute("onqueue",mailQueueService.countMailsbyDate(date));

        return "statistics/index";
    }
}
