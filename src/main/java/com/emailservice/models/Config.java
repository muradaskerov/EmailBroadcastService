package com.emailservice.models;

import javax.persistence.*;

/**
 * Created by murad on 10/19/16.
 */
@Entity
@Table(name = "config")
public class Config {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @Column(name = "mail_host")
    String mail_host;
    @Column(name = "mail_port")
    Integer mail_port;
    @Column(name = "mail_smtp_auth", columnDefinition = "BOOL NOT NULL DEFAULT 1")
    Integer mail_smtp_auth = 1;
    @Column(name = "mail_smtp_starttls_enable",columnDefinition = "BOOL NOT NULL DEFAULT 1")
    Integer mail_smtp_starttls_enable = 1;
    @Column(name = "mail_fullmail")
    String mail_fullmail;
    @Column(name = "mail_username")
    String mail_username;
    @Column(name = "mail_password")
    String mail_password;
    @Column(name = "mail_cronjob")
    String mail_cronjob;
    @Column(name = "mail_fromname")
    String mail_fromname;
    @Column(name = "mail_transport_protocol")
    String mail_transport_protocol;
    @Column(name = "mail_daily_limit")
    Integer mail_daily_limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail_host() {
        return mail_host;
    }

    public void setMail_host(String mail_host) {
        this.mail_host = mail_host;
    }

    public Integer getMail_port() {
        return mail_port;
    }

    public void setMail_port(Integer mail_port) {
        this.mail_port = mail_port;
    }

    public Integer getMail_smtp_auth() {
        return mail_smtp_auth;
    }

    public void setMail_smtp_auth(Integer mail_smtp_auth) {
        this.mail_smtp_auth = mail_smtp_auth;
    }

    public Integer getMail_smtp_starttls_enable() {
        return mail_smtp_starttls_enable;
    }

    public void setMail_smtp_starttls_enable(Integer mail_smtp_starttls_enable) {
        this.mail_smtp_starttls_enable = mail_smtp_starttls_enable;
    }

    public String getMail_fullmail() {
        return mail_fullmail;
    }

    public void setMail_fullmail(String mail_fullmail) {
        this.mail_fullmail = mail_fullmail;
    }

    public String getMail_username() {
        return mail_username;
    }

    public void setMail_username(String mail_username) {
        this.mail_username = mail_username;
    }

    public String getMail_password() {
        return mail_password;
    }

    public void setMail_password(String mail_password) {
        this.mail_password = mail_password;
    }

    public String getMail_cronjob() {
        return mail_cronjob;
    }

    public void setMail_cronjob(String mail_cronjob) {
        this.mail_cronjob = mail_cronjob;
    }

    public String getMail_fromname() {
        return mail_fromname;
    }

    public void setMail_fromname(String mail_fromname) {
        this.mail_fromname = mail_fromname;
    }

    public String getMail_transport_protocol() {
        return mail_transport_protocol;
    }

    public void setMail_transport_protocol(String mail_transport_protocol) {
        this.mail_transport_protocol = mail_transport_protocol;
    }

    public Integer getMail_daily_limit() {
        return mail_daily_limit;
    }

    public void setMail_daily_limit(Integer mail_daily_limit) {
        this.mail_daily_limit = mail_daily_limit;
    }

    public Config(String mail_host, Integer mail_port, Integer mail_smtp_auth, Integer mail_smtp_starttls_enable, String mail_fullmail, String mail_username, String mail_password, String mail_cronjob, String mail_fromname,String mail_transport_protocol,Integer mail_daily_limit) {
        this.mail_host = mail_host;
        this.mail_port = mail_port;
        this.mail_smtp_auth = mail_smtp_auth;
        this.mail_smtp_starttls_enable = mail_smtp_starttls_enable;
        this.mail_fullmail = mail_fullmail;
        this.mail_username = mail_username;
        this.mail_password = mail_password;
        this.mail_cronjob = mail_cronjob;
        this.mail_fromname = mail_fromname;
        this.mail_transport_protocol = mail_transport_protocol;
        this.mail_daily_limit = mail_daily_limit;

    }
    

    public Config(){

    }


}
