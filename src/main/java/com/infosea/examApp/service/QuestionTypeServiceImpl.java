package com.infosea.examApp.service;

import com.infosea.examApp.dao.QuestionTypeDao;
import com.infosea.examApp.pojo.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    @Autowired
    QuestionTypeDao questionTypeDao;
    @Override
    public QuestionType find(long id) {
        return questionTypeDao.find(id);
    }

    @Override
    public long save(QuestionType questionType) {
        return (long)questionTypeDao.save(questionType);
    }
}
