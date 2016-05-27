package com.infosea.examApp.service;

import com.infosea.examApp.dao.*;
import com.infosea.examApp.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public class ExamServiceImpl  implements ExamService{
    @Autowired
    ExamDao examDao;
    @Autowired
    CommonDao commonDao;

    @Autowired
    TestPaperDao testPaperDao;

    @Autowired
    QuestionDao questionDao;
    @Autowired
    TestPaperDefineDao testPaperDefineDao;
    @Override
    @Transactional
    public Exam findByID(long id) {
       Exam exam = examDao.findByID(id);
        return exam;
    }

    @Override
    @Transactional
    public long save(Exam exam) {
        long examid =(long)examDao.save(exam);
       return  examid;
    }

    @Override
    public Exam findByEidAndUid(long eid, long uid) {
        return examDao.findByEidAndUid(eid,uid);
    }

    @Override
    public List<Exam> findAll() {
        return examDao.findAll();
    }

    @Override
    public void delete(long id) {
        Exam exam =examDao.findByID(id);
        examDao.del(exam);
    }

    @Override
    public void update(Exam exam) {
        examDao.update(exam);
    }

    @Override
    public List<Exam> findByUserId(long uid) {
        return examDao.findByUserId(uid);
    }
    @Transactional
    @Override
    public List<Exam> findALl(String hql) {
        return examDao.findAll(hql);
    }


}
