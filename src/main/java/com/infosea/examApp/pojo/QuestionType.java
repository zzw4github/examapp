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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn( name = "type_id")
    private Type type;

    @Column
    private int score;
    @Column
    private int amount;

    public QuestionType() {
    }

    public QuestionType(Type type, int score, int amount) {
        this.type = type;
        this.score = score;
        this.amount = amount;
    }

    //    @OneToMany(fetch = FetchType.EAGER)
//    private List<Question> questions = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

//    public List<Question> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<Question> questions) {
//        this.questions = questions;
//    }

//    public TestPaperDefine getTestPaperDefine() {
//        return testPaperDefine;
//    }
//
//    public void setTestPaperDefine(TestPaperDefine testPaperDefine) {
//        this.testPaperDefine = testPaperDefine;
//    }
}
