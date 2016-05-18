package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by infosea on 2016/4/19.
 * 用户表
 */
@Entity
public class User {
    @Id
    @Column
    @GenericGenerator(name = "increment" ,strategy = "increment")
   @GeneratedValue(generator = "increment")
    private long id;
    @Column
    private String tmh;
    @Column
    private String name;

    @Column
    private String sex;

    @Column
    private String yjdw;


    @Column
    private  String ejdw;

    @Column
    private String card_number;

    @Column(updatable = false)
    private String pwd;

    private String permission;

    @Column
    private String situation;

    public User() {
    }

    public User(String tmh, String name) {
        this.tmh = tmh;
        this.name = name;
    }

    public String getTmh() {
        return tmh;
    }

    public void setTmh(String tmh) {
        this.tmh = tmh;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYjdw() {
        return yjdw;
    }

    public void setYjdw(String yjdw) {
        this.yjdw = yjdw;
    }

    public String getEjdw() {
        return ejdw;
    }

    public void setEjdw(String ejdw) {
        this.ejdw = ejdw;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

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



    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }



    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }


}
