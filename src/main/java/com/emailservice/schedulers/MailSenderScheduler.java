package com.emailservice.schedulers;

import com.emailservice.helpers.CommonHelper;
import com.emailservice.models.MailQueue;
import com.emailservice.models.SentMails;
import com.emailservice.services.ConfigService;
import com.emailservice.services.SentMailsService;
import com.emailservice.tasks.MailSenderTask;
import com.emailservice.services.MailQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by murad on 10/15/16.
 */

@Component("mailSenderScheduler")
public class MailSenderScheduler {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    MailQueueService mailQueueService;

    @Autowired
    SentMailsService sentMailsService;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    Environment environment;

    @Autowired
    ConfigService configService;

    private static final Logger logger = LoggerFactory.getLogger(MailSenderScheduler.class);

    public void run(){
        logger.info("MailScheduler started");

        JavaMailSender mailSender = (JavaMailSender) applicationContext.getBean("mailSender");

        String fromName = configService.getConfig().get(0).getMail_fromname();
        int order = 1;

        List<MailQueue> mailQueues = mailQueueService.getMailsByDate(CommonHelper.getCurrentDate("yyyy-MM-dd"),1,1000);
        for(MailQueue mailQueue : mailQueues){
            logger.debug("execute mail no: {}",order);
            taskExecutor.execute(new MailSenderTask(mailQueue.getId(),mailQueue.getFrom(),fromName,mailQueue.getTo(),mailQueue.getSubject(),mailQueue.getContent(),order,mailSender,mailQueueService,sentMailsService));
            order++;
        }

        logger.info("MailScheduler finished");
    }


}
