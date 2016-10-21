package com.emailservice.models;

import javax.persistence.*;

/**
 * Created by murad on 10/8/16.
 */

@Entity
@Table(name = "blacklist")
public class Blacklist {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="mail")
    private String mail;

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

    public Blacklist(String mail) {
        this.mail = mail;
    }
    public Blacklist() {

    }
}
