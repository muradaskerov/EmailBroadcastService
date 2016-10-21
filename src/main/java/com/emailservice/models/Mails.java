package com.emailservice.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

/**
 * Created by murad on 10/5/16.
 */
@Entity
@Table(name = "mails")
public class Mails {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="mail")
    private String mail;

    @Column(name="`group`")
    private Integer group;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Mails(String mail, Integer group) {
        this.mail = mail;
        this.group = group;
    }

    public  Mails(){}
}
