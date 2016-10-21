package com.emailservice.models;

import javax.persistence.*;

/**
 * Created by murad on 10/15/16.
 */

@Entity
@Table(name = "mailqueue")
public class MailQueue {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @Column(name = "`from`")
    String from;
    @Column(name = "`to`")
    String to;
    @Column(name = "`subject`")
    String subject;
    @Column(name = "content")
    String content;
    @Column(name = "sendDate")
    String sendDate;
    @Column(name = "inserted")
    String inserted;
    @Column(name = "`status`")
    String status;

    public MailQueue(String from, String to, String subject, String content, String sendDate, String inserted, String status) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.sendDate = sendDate;
        this.inserted = inserted;
        this.status = status;
    }

    public MailQueue(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getInserted() {
        return inserted;
    }

    public void setInserted(String inserted) {
        this.inserted = inserted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
