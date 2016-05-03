package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by infosea on 2016/4/20.
 */
@Entity
@Table(name= "questiontype")
public class QuestionType {
    @Id
    @GenericGenerator(name = "increment" ,strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;
    @Column(name="desc")
    private String desc;
    @Column
    private String score;
    @Column
    private int amount;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
