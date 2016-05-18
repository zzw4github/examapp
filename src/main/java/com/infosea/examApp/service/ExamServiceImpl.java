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
    public Exam findExamByID(long id) {
       Exam exam = examDao.findExamByID(id);
        return exam;
    }

    @Override
    @Transactional
    public long save(Exam exam) {
        long examid =(long)examDao.save(exam);
       return  examid;
    }

    @Override
    public Exam findExamByEidAndUid(long eid, long uid) {
        return examDao.findExamByEidAndUid(eid,uid);
    }

    @Override
    public List<Exam> findAll() {
        return examDao.findAllExam();
    }

    @Override
    public void delete(long id) {
        Exam exam =examDao.findExamByID(id);
        examDao.del(exam);
    }

    @Override
    public void update(Exam exam) {
        examDao.update(exam);
    }



    @Override
    public List<Exam> findByUserId(long uid) {
        return examDao.queryByUserId(uid);
    }

    @Override
    public List<Exam> findALl(String hql) {
        return examDao.selectAll(hql);
    }
}
