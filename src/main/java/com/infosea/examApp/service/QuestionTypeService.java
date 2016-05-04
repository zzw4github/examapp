package com.infosea.examApp.service;

import com.infosea.examApp.pojo.QuestionType;

/**
 * Created by infosea on 2016/4/20.
 */
public interface QuestionTypeService {
    QuestionType find(long id);
    long save(QuestionType questionType);
}
