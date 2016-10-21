package com.emailservice.controllers;

import com.emailservice.helpers.CommonHelper;
import com.emailservice.models.Blacklist;
import com.emailservice.models.Config;
import com.emailservice.models.MailQueue;
import com.emailservice.models.Mails;
import com.emailservice.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by murad on 10/2/16.
 */
@Controller
public class MailController {

    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    MailService mailService;

    @Autowired
    GroupsService groupsService;

    @Autowired
    TemplatesService templatesService;

    @Autowired
    BlacklistService blacklistService;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    MailQueueService mailQueueService;

    @Autowired
    ConfigService configService;

    @RequestMapping(value = "/mails/send",method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute("groups",groupsService.getGroups());
        model.addAttribute("templates",templatesService.getTemplates());

        return "mails/send";
    }

    @RequestMapping(value = "/mails/send",method = RequestMethod.POST)
    public String sendmail(HttpServletRequest request){
        ArrayList<String> recipients = new ArrayList<String>();

        if(!request.getParameter("recipient").trim().equals("")){

            String[] recipientAdressArray = request.getParameter("recipient").trim().split(",");
            for(String tmp : recipientAdressArray) recipients.add(tmp);
        }

        Config config = configService.getConfig().get(0);

        String subject = "",message = "",sendDate;
        int countRecipients;
        int limit = config.getMail_daily_limit();
        logger.info("limit is {}",limit);
        String fromFullmail = config.getMail_fullmail();
        String currentDate = CommonHelper.getCurrentDate("yyyy-MM-dd HH:mm:ss");


        if(!request.getParameter("group").trim().equals("")){
            int group = Integer.parseInt(request.getParameter("group").trim());
            List<Mails> mailsByGroup = mailService.getMailsbyGroup(group,0,limit);
            for(Mails mail : mailsByGroup) recipients.add(mail.getMail());
        }

        //blacklist filter
        recipients = filterfromBlacklist(recipients);
        countRecipients = recipients.size();

        if(!request.getParameter("subject").trim().equals(""))
            subject = request.getParameter("subject").trim();

        if(!request.getParameter("content").trim().equals(""))
            message = request.getParameter("content").trim();

        subject = subject.trim();
        message = message.trim();

        if(countRecipients > 0 && !subject.equals("") && !message.equals("")){
            ArrayList<MailQueue> mailQueues = new ArrayList<MailQueue>();
            int interval = 0;
            int order = 1;

            ArrayList<Object> sendDateAndInterval = getSendDateAndInterval(interval,limit);

            interval = (Integer) sendDateAndInterval.get(0);
            sendDate = (String) sendDateAndInterval.get(1);

            int dynamicLimit = limit - mailQueueService.countMailsbyDate(sendDate);

            for(String recipientAddress : recipients){
                if(order > dynamicLimit){
                    if(order >= limit)
                        order = 1;
                    interval++;
                    sendDateAndInterval = getSendDateAndInterval(interval,limit);

                    interval = (Integer) sendDateAndInterval.get(0);
                    sendDate = (String) sendDateAndInterval.get(1);
                    dynamicLimit = limit - (Integer) sendDateAndInterval.get(2);
                }
                mailQueues.add(new MailQueue(fromFullmail,recipientAddress,subject,message,sendDate,currentDate,"1"));
                order++;
            }
            mailQueueService.addMail(mailQueues);
        }else
            logger.info("No mails to send.");

        return "redirect:/mails/send";
    }

    @RequestMapping(value = "/mails/list",method = RequestMethod.GET)
    public String mails(Model model, @RequestParam(value = "page", required = false) Integer pageParam){
        int limit = 10;
        int page = (pageParam != null ) ? pageParam : 1;
        int countMails = mailService.countMails();
        model.addAttribute("mails",mailService.getMails(page,limit));
        model.addAttribute("countMails",countMails);
        model.addAttribute("countPages",Math.ceil((double) countMails/limit));

        return "mails/list";
    }

    @RequestMapping(value = "/mails/add",method = RequestMethod.GET)
    public String mailsAdd(Model model){
        model.addAttribute("groups",groupsService.getGroups());
        return "mails/add";
    }
    @RequestMapping(value = "/mails/add",method = RequestMethod.POST)
    public String mailsAddPost(HttpServletRequest request,@RequestParam(value = "excelfile",required=false) MultipartFile file, @ModelAttribute Mails mails){

        ArrayList<Mails> mailObject = new ArrayList<Mails>();

        String mailsAddressString = request.getParameter("mailswithcomma");
        String[] mailsAdressArray = mailsAddressString.split(",");
        String filename = "";

        String uploadPath = request.getSession().getServletContext().getRealPath("/resources/uploads/");

        if(file != null){
            filename = String.valueOf(System.currentTimeMillis())+file.getOriginalFilename();
        }

        if(CommonHelper.uploadFile(file,uploadPath,filename)){
            logger.info("successfully upload");
            List<String> mailListFromExcel = CommonHelper.readFromExcel(uploadPath+filename);

            for (String mailAdress : mailListFromExcel){
                mailObject.add(new Mails(mailAdress,mails.getGroup()));
            }
        }else{
            logger.info("not uploaded");
        }

        for(String mailAdress : mailsAdressArray){
            mailObject.add(new Mails(mailAdress,mails.getGroup()));
        }

        if(mailObject.size() > 0){
            try{
                mailService.addMail(mailObject);
                logger.info("Added all {} mails successfully",mailObject.size());
            }catch (Exception e){
                logger.error("Not inserted. Error: {}",e.getMessage());
            }
        }else{
            logger.info("No mails to insert");
        }


        return "redirect:/mails/list";
    }

    @RequestMapping(value = "/mails/delete",method = RequestMethod.GET)
    public String mailsDelete(@RequestParam(value = "id") Integer id){
        mailService.deleteMail(id);

        return "redirect:/mails/list";
    }

    @RequestMapping(value = "/mails/config",method = RequestMethod.GET)
    public String mailsConfig(Model model){
        Config config = configService.getConfig().get(0);
        model.addAttribute("config",config);

        return "mails/config";
    }
    @RequestMapping(value = "/mails/config/save",method = RequestMethod.POST)
    public String mailsConfigSave(@ModelAttribute Config config){
        configService.updateConfig(config);

        return "redirect:/mails/config";
    }

    private ArrayList<String> filterfromBlacklist(ArrayList<String> recipients){
        ArrayList<String> intersected_mails = new ArrayList<String>();
        ArrayList<String> blacklistMails = new ArrayList<String>();

        logger.info("Recipients: "+ recipients);

        List<Blacklist> blacklistObject =  blacklistService.getMails();

        for(Blacklist mail : blacklistObject) blacklistMails.add(mail.getMail());
        intersected_mails.addAll(recipients);

        intersected_mails.retainAll(blacklistMails);
        logger.info("Intersected mails: "+intersected_mails);

        recipients.removeAll(intersected_mails);
        logger.info("Recipients after remove: "+ recipients);

        return recipients;
    }

    public ArrayList<Object> getSendDateAndInterval(int interval,int limit){
        ArrayList<Object> result = new ArrayList<Object>();

        String sendDate = CommonHelper.getCurrentDate("yyyy-MM-dd");

        if(interval > 0){
            sendDate = CommonHelper.getDateInterval("yyyy-MM-dd",+interval);
        }

        int countQueuebyDate = mailQueueService.countMailsbyDate(sendDate);

        if(countQueuebyDate >= limit){
            interval++;
            return getSendDateAndInterval(interval, limit);
        }


        result.add(0,interval);
        result.add(1,sendDate);
        result.add(2,countQueuebyDate);

        return result;
    }




}
