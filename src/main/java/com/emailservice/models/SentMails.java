package com.emailservice.models;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by murad on 10/18/16.
 */
@Entity
@Table(name = "sentmails")
public class SentMails {
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
    @Column(name = "sentDate")
    String sentDate;
    @Column(name = "inserted")
    String inserted;

    public SentMails(String from, String to, String subject, String content, String sentDate, String inserted) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.sentDate = sentDate;
        this.inserted = inserted;
    }
    public SentMails(){};

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

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getInserted() {
        return inserted;
    }

    public void setInserted(String inserted) {
        this.inserted = inserted;
    }
}
