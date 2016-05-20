package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by infosea on 2016/5/20.
 */
@Entity
public class Subject {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @OneToOne
    Question question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    int score;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
