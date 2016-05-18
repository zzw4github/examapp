package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name="intro")
    private String desc;

    @Column
    private int score;
    @Column
    private int amount;

//    @ManyToOne
//    private TestPaperDefine testPaperDefine;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();

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


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

//    public TestPaperDefine getTestPaperDefine() {
//        return testPaperDefine;
//    }
//
//    public void setTestPaperDefine(TestPaperDefine testPaperDefine) {
//        this.testPaperDefine = testPaperDefine;
//    }
}
