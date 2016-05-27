package com.infosea.examApp.pojo;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
@Entity
public class Question {
    @Id
    @GenericGenerator(name = "increment" ,strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

     @Column(name="q_content")
    private String content;

    @Column(name="std_answer", nullable = false)
    private String stdAnswer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "type_id")
    private Type type;

    @Column(name = "q_option")
    String option;

    @Column(name="q_desc")
    private String desc;

    @Temporal(TemporalType.DATE)
    @Column(name="q_date" ,  nullable = false, length = 10)
    private Date date;

    public Question() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


    public String getStdAnswer() {
        return stdAnswer;
    }

    public void setStdAnswer(String stdAnswer) {
        this.stdAnswer = stdAnswer;
    }
}
