package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by infosea on 2016/4/22.
 */
@Entity
public class Answer {
    @Id
    @GenericGenerator(name = "increment" ,strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;
    @Column
    private String answer;
    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    @ManyToOne()
    private TestPaper testPaper;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TestPaper getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(TestPaper testPaper) {
        this.testPaper = testPaper;
    }

}
