package com.infosea.examApp.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column private  String use;
//    @ManyToMany(mappedBy ="id" )
//    List<User> users = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<QuestionType> questionTypes = new ArrayList<>();


    public TestPaperDefine() {
    }



    public TestPaperDefine(String name) {
        this.name = name;
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

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
