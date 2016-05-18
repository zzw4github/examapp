package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Question;

import java.io.Serializable;
import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionDao {
    Serializable save(Question querstion);

    Question find(long id);

    List<Question> findAll();

    List<Question> findByHQL(String hql);

    void update(Question question);

    void delete(Question question);
}
