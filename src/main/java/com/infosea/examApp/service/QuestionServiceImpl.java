package com.infosea.examApp.service;

import com.infosea.examApp.dao.QuestionDao;
import com.infosea.examApp.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    QuestionDao questionDao;
    @Override
    public long save(Question question) {
        return(long) questionDao.save(question);
    }

    @Override
    public Question find(long id) {
        return questionDao.find(id);
    }

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public List<Question> findByHQL(String hql){
        return questionDao.findByHQL(hql);
    }

    @Override
    public void update(Question question) {
        questionDao.update(question);
    }
}
