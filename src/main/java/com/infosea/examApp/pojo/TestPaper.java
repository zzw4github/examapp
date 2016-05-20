package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by infosea on 2016/4/25.
 */
@Entity
public class TestPaper {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;
    @Column
    private String name;

    @OneToOne
    TestPaperDefine testPaperDefine;

    @OneToOne(mappedBy = "testPaper")
    Exam exam;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    private  User user;

    @Column
    private int score;

    public TestPaper() {
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "begin_time")
    private Date beginTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_time")
    private Date endTime;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public TestPaperDefine getTestPaperDefine() {
        return testPaperDefine;
    }

    public void setTestPaperDefine(TestPaperDefine testPaperDefine) {
        this.testPaperDefine = testPaperDefine;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
