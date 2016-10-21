package com.emailservice.config;

import com.emailservice.models.Config;
import com.emailservice.services.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by murad on 10/19/16.
 */

@Configuration
public class SMTPConfiguration {

    private String protocol;

    private String host;

    private int port;

    private boolean auth;

    private boolean starttls;

    private String username;

    private String password;

    @Autowired
    ConfigService configService;

    private static final Logger logger = LoggerFactory.getLogger(SMTPConfiguration.class);

    @Bean
    @Scope("prototype")
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Config config = configService.getConfig().get(0);

        Properties mailProperties = new Properties();

        this.auth = (config.getMail_smtp_auth() != 0);
        this.starttls = (config.getMail_smtp_starttls_enable() != 0);
        this.host = config.getMail_host();
        this.port = config.getMail_port();
        this.username = config.getMail_username();
        this.password = config.getMail_password();
        this.protocol = config.getMail_transport_protocol();

        logger.info("smtp configuration setted from db.");

        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        return mailSender;
    }
}
