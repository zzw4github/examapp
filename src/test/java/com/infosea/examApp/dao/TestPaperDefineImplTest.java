package com.infosea.examApp.dao;

import com.infosea.examApp.dao.QuestionDao;
import com.infosea.examApp.dao.QuestionTypeDao;
import com.infosea.examApp.dao.TestPaperDefineDao;
import com.infosea.examApp.pojo.Question;
import com.infosea.examApp.pojo.QuestionType;
import com.infosea.examApp.pojo.TestPaperDefine;
import com.infosea.examApp.pojo.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by infosea on 2016/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class TestPaperDefineImplTest {
    Logger logger = LogManager.getLogger(TestPaperDefineImplTest.class);
    @Resource
    TestPaperDefineDao testPaperDefineDao;
    @Resource
    QuestionTypeDao questionTypeDao;
    @Resource
    QuestionDao questionDao;
    @Resource
    UserDao userDao;
    @Test
    public void testSave() throws Exception {
        TestPaperDefine testPaperDefine = new TestPaperDefine("试卷一（包含选择题判断题多选题）");
        User user = userDao.find(2);
        QuestionType slgc = questionTypeDao.find(1L);
        QuestionType mugc = questionTypeDao.find(2L);
        QuestionType tof = questionTypeDao.find(3L);

        Question question1 = questionDao.find(11L);
        Question question2 = questionDao.find(12L);
        Question question3 = questionDao.find(13L);
        Question question4 = questionDao.find(14L);
        Question question5 = questionDao.find(15L);
        Question question6 = questionDao.find(16L);
        Question question7 = questionDao.find(17L);

        slgc.getQuestions().add(question1);
        slgc.getQuestions().add(question2);
        slgc.getQuestions().add(question3);
        slgc.getQuestions().add(question4);
        slgc.getQuestions().add(question5);
        slgc.getQuestions().add(question6);
        slgc.getQuestions().add(question7);

        testPaperDefine.getQuestionTypes().add(slgc);
        testPaperDefine.getQuestionTypes().add(mugc);
        testPaperDefine.getQuestionTypes().add(tof);
        testPaperDefineDao.save(testPaperDefine);
        user.getTestPaperDefines().add(testPaperDefine);
        userDao.saveOrUpdate(user);
        logger.log(Level.INFO,"this is a message");

    }
}