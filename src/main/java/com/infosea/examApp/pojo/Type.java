package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by infosea on 2016/5/19.
 */
@Entity
public class Type {
    @Id
    @GenericGenerator(name = "increment" ,strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column
    private String name;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
