package com.infosea.examApp.service;

import com.infosea.examApp.dao.ExamDao;
import com.infosea.examApp.dao.TestPaperDefineDao;
import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.QuestionType;
import com.infosea.examApp.pojo.TestPaperDefine;
import com.infosea.examApp.pojo.User;
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

    public Exam produceExam(User user,long testPaperId){
        Exam exam = new Exam();
        TestPaperDefine testPaperDefine = testPaperDefineDao.findById(testPaperId);
        List<QuestionType> questionTypes = testPaperDefine.getQuestionTypes();
        for(int i=0;i<questionTypes.size();i++){
            QuestionType questionType = questionTypes.get(i);
            int amount =questionType.getAmount();
            questionType.getDesc();
        }
        exam.setUser(user);
        exam.setTestPaperDefine();


    }
}
