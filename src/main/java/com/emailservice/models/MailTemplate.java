package com.emailservice.models;

import javax.persistence.*;

/**
 * Created by murad on 10/8/16.
 */
@Entity
@Table(name = "templates")
public class MailTemplate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @Column(name = "title")
    String title;
    @Column(name = "text")
    String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
