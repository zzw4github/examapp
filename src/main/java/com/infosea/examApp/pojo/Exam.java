package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 表示一个读者的一次考试
 * Created by infosea on 2016/4/20.
 */
@Entity
public class Exam {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List< User> users;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answer = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, length = 10, name = "examdate")
    private Date date;

    @Column
    private int time;

    @Column(name = "examdesc")
    private String desc;

    @Column
    private String validFlag;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestPaper> testPapers;




    public Exam() {
    }


    public long getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswers(List<Answer> answer) {
        this.answer = answer;
    }



    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<TestPaper> getTestPapers() {
        return testPapers;
    }

    public void setTestPapers(List<TestPaper> testPapers) {
        this.testPapers = testPapers;
    }
}
