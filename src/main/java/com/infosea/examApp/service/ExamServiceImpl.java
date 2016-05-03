package com.infosea.examApp.service;

import com.infosea.examApp.dao.ExamDao;
import com.infosea.examApp.pojo.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public class ExamServiceImpl  implements ExamService{
    @Autowired
    ExamDao examDao;
    @Override
    @Transactional
    public Exam findExam(long id) {
       Exam exam = examDao.findExam(id);
        return exam;
    }
    @Transactional
    @Override
    public long save(Exam exam) {
        long examid =(long)examDao.save(exam);
       return  examid;
    }
    @Transactional
    @Override
    public Exam findExamByExamIdandUserId(long eid, long uid) {
        return examDao.findExamByExamIdandUserId(eid,uid);
    }
    @Transactional
    @Override
    public List<Exam> findAll() {
        return examDao.findAllExam();
    }
    @Transactional
    @Override
    public void delete(long id) {
        Exam exam =examDao.findExam(id);
        examDao.del(exam);
    }
    @Transactional
    @Override
    public void update(Exam exam) {
        examDao.update(exam);
    }
}
