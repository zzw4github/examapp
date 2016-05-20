package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by infosea on 2016/5/17.
 */
@Entity
public class TestPaperDefine {
    @Id
    @GenericGenerator(name = "increment" ,strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;
    @Column(name="tpd_name")
    private String name;

    @Column
    private  String valid;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, length = 10, name = "define_date")
    private Date defineDate;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<QuestionType> questionTypes = new ArrayList<>();


    public TestPaperDefine() {
    }


    public TestPaperDefine(String name) {
        this.name = name;
    }

    public TestPaperDefine(String name, Date defineDate) {
       this(name);
        this.defineDate = defineDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<QuestionType> getQuestionTypes() {
        return questionTypes;
    }

    public void setQuestionTypes(List<QuestionType> questionTypes) {
        this.questionTypes = questionTypes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public Date getDefineDate() {
        return defineDate;
    }

    public void setDefineDate(Date defineDate) {
        this.defineDate = defineDate;
    }

    //    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
