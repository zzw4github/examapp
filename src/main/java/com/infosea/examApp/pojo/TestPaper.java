package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column
    private String questions_id;

    @OneToOne
    TestPaperDefine testPaperDefine;

    @OneToOne(mappedBy = "testPaper")
    Exam exam;

    @ManyToOne
    private  User user;
            ;
    public TestPaper() {
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

    public String getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(String questions_id) {
        this.questions_id = questions_id;
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
}
