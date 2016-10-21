package com.emailservice.tasks;


import com.emailservice.helpers.CommonHelper;
import com.emailservice.models.SentMails;
import com.emailservice.services.MailQueueService;
import com.emailservice.services.SentMailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * Created by murad on 10/4/16.
 */

public class MailSenderTask implements Runnable {

    String recipientAddress,subject,message,fullmail,from,currentDate,currentTime;
    int i,id;
    JavaMailSender mailsender;

    MailQueueService mailQueueService;
    SentMailsService sentMailsService;

    private static final Logger logger = LoggerFactory.getLogger(MailSenderTask.class);

    public MailSenderTask(Integer id,String fullmail, String from, String recipientAddress,String subject,String message, int say,JavaMailSender mailSender,MailQueueService mailQueueService,SentMailsService sentMailsService){
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.message = message;
        this.i = say;
        this.mailsender = mailSender;
        this.from = from;
        this.fullmail = fullmail;
        this.id = id;
        this.mailQueueService = mailQueueService;
        this.sentMailsService = sentMailsService;

        this.currentDate = CommonHelper.getCurrentDate("yyyy-MM-dd");
        this.currentTime = CommonHelper.getCurrentDate("HH:mm:ss");
    }

    public void run() {

        MimeMessage message = mailsender.createMimeMessage();
        try{
            // use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientAddress);
            helper.setSubject(subject);
            helper.setFrom(new InternetAddress(fullmail,from));

            // use the true flag to indicate the text included is HTML
            helper.setText(this.message, true);
        }catch (MessagingException me){
            logger.error("error: {} ",me.getMessage());
        }
        catch (UnsupportedEncodingException uee){
            logger.error("error: {} ",uee.getMessage());
        }

        mailsender.send(message);
        logger.info("Mail no: {} sent.",i);

        sentMailsService.addMail(new SentMails(fullmail,recipientAddress,subject,this.message,currentDate,currentDate+" "+currentTime));
        logger.info("Mail no {} inserted to sentmails table.",i);

        mailQueueService.deleteMail(id);
        logger.info("Mail no {} deleted from queue.",i);

    }

}
