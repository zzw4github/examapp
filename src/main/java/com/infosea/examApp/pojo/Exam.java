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

    @OneToOne
    private User user;

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



    @OneToOne
    @JoinColumn(name = "testPaper_id")
    private TestPaper testPaper;

    @Temporal(TemporalType.DATE)
    @Column(name = "begin_time")
    private Date beginTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "examscore")
    private long score;

    public Exam() {
    }


    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public TestPaper getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(TestPaper testPaper) {
        this.testPaper = testPaper;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswers(List<Answer> answer) {
        this.answer = answer;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
