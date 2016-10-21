package com.emailservice.controllers;

import com.emailservice.helpers.CommonHelper;
import com.emailservice.models.Blacklist;
import com.emailservice.services.BlacklistService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by murad on 10/8/16.
 */
@Controller
public class BlacklistController {

    @Autowired
    BlacklistService blacklistService;

    private static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);


    @RequestMapping(value = "/blacklist/list",method = RequestMethod.GET)
    public String mails(Model model, @RequestParam(value = "page", required = false) Integer pageParam){
        int limit = 10;
        int page = (pageParam != null ) ? pageParam : 1;
        int countMails = blacklistService.countMails();
        model.addAttribute("mails",blacklistService.getMails(page,limit));
        model.addAttribute("countPages",Math.ceil((double) countMails/limit));

        return "blacklist/list";
    }

    @RequestMapping(value = "/blacklist/add",method = RequestMethod.GET)
    public String mailsAdd(){

        return "blacklist/add";
    }

    @RequestMapping(value = "/blacklist/add",method = RequestMethod.POST)
    public String mailsAddPost(HttpServletRequest request, @RequestParam(value = "excelfile",required=false) MultipartFile file, @ModelAttribute Blacklist mails){

        ArrayList<Blacklist> mailObject = new ArrayList<Blacklist>();

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
                mailObject.add(new Blacklist(mailAdress));
            }
        }else{
            logger.info("not uploaded");
        }

        for(String mailAdress : mailsAdressArray){
            mailObject.add(new Blacklist(mailAdress));
        }

        if(mailObject.size() > 0){
            try{
                blacklistService.addMail(mailObject);
                logger.info("Added all {} mails succesfully",mailObject.size());
            }catch (Exception e){
                logger.error("Not inserted. Error: {}",e.getMessage());
            }
        }else{
            logger.info("No mails to insert");
        }

        return "redirect:/blacklist/list";
    }

    @RequestMapping(value = "/blacklist/delete",method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Integer id){
        blacklistService.deleteMail(id);

        return "redirect:/blacklist/list";
    }
}
