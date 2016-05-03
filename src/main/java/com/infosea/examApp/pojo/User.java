package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by infosea on 2016/4/19.
 */
@Entity
public class User {
    @Id
    @Column
    @GenericGenerator(name = "increment" ,strategy = "increment")
   @GeneratedValue(generator = "increment")
    private long id;
    @Column
    private String name;
    @Column
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
