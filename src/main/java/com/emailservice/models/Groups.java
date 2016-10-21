package com.emailservice.models;

import javax.persistence.*;

/**
 * Created by murad on 10/8/16.
 */
@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
