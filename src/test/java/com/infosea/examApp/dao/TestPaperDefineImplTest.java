package com.infosea.examApp.dao;

import com.infosea.examApp.dao.QuestionDao;
import com.infosea.examApp.dao.QuestionTypeDao;
import com.infosea.examApp.dao.TestPaperDefineDao;
import com.infosea.examApp.pojo.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    TypeDao typeDao;

    @Test
//    @Transactional
    /**
     * 定义试卷包含那些题型，及题型的分数，个数
     */
    public void testSave() throws Exception {
        TestPaperDefine testPaperDefine = new TestPaperDefine("试卷一（包含选择题判断题多选题）");
        testPaperDefine.setValid("YES");
        User user = userDao.find(2);

        Type type1  = typeDao.findById(1);
        Type type2  = typeDao.findById(2);
        Type type3  = typeDao.findById(3);


        QuestionType questionType1 = new QuestionType();
        QuestionType questionType2 = new QuestionType();
        QuestionType questionType3 = new QuestionType();

        questionType1.setAmount(20);
        questionType1.setScore(2);
        questionType1.setType(type1);

        questionType2.setAmount(10);
        questionType2.setScore(1);
        questionType2.setType(type2);

        questionType3.setAmount(10);
        questionType3.setScore(3);
        questionType3.setType(type3);

        questionTypeDao.save(questionType1);
        questionTypeDao.save(questionType2);
        questionTypeDao.save(questionType3);

        testPaperDefine.getQuestionTypes().add(questionType1);
        testPaperDefine.getQuestionTypes().add(questionType2);
        testPaperDefine.getQuestionTypes().add(questionType3);

        testPaperDefineDao.save(testPaperDefine);
        testPaperDefineDao.flush();
        logger.log(Level.INFO,"this is a message");

    }
}