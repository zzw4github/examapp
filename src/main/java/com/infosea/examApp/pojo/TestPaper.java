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
    @JoinColumn(name = "define_id",
            foreignKey = @ForeignKey(name = "DEFINE_ID_FK")
    )
    TestPaperDefine testPaperDefine;

    @ManyToOne
    @JoinColumn(name= "exam_id",
            foreignKey = @ForeignKey(name = "EXAM_ID_FK")
    )
    private Exam exam;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name= "user_id",
                    foreignKey = @ForeignKey(name = "USER_ID_FK")
    )
    private  User user;

    @Column
    private Integer score;

@Column(name="cost_time")
    Long costTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "begin_time")
    private Date beginTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;

    public TestPaper() {
    }
    public TestPaper(String name) {
        this.name = name;
    }

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
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

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }
}
