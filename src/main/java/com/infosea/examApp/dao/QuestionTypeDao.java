package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.QuestionType;

import java.io.Serializable;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionTypeDao {


    Serializable save(QuestionType questionType);

    void delete(QuestionType questionType);

    void update(QuestionType questionType);

    QuestionType find(long id);


}
