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
    @Column
    private String content;
    private String stdAnswer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "type_id")
//    //这个设为双向绑定的时候 mappedby不能是 QuestionType
////    不知道能不能设为双向
    private Type type;

    @OneToOne(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "option_id")
    private Option option ;

    @Column(name="desc1")
    private String desc;

    @Temporal(TemporalType.DATE)
    @Column( nullable = false, length = 10)
    private Date date;

    public long getId() {
        return id;
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

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStdAnswer() {
        return stdAnswer;
    }

    public void setStdAnswer(String stdAnswer) {
        this.stdAnswer = stdAnswer;
    }
}
