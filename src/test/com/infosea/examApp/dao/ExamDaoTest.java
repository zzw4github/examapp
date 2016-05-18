package com.infosea.examApp.dao;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.infosea.examApp.pojo.*;
import com.infosea.examApp.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by infosea on 2016/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class ExamDaoTest {
    @Resource
    ExamDao examDao;
    @Resource
    UserDao userDao;
    @Resource
    TestPaperDao testPaperDao;
    @Resource
    QuestionService questionService;

    @Test
    public void testFindExamByID() throws Exception {
        Logger logger = LoggerFactory.getLogger(ExamDaoTest.class);
        Exam exam = examDao.findExamByID(47);
        logger.info(exam.getDesc());


    }

    @Test
    public void testFindAllExam() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        Exam exam = new Exam();
        exam.setDate(new Date());

        TestPaper testPaper = testPaperDao.findTestPaperById(1);
        exam.setTestPaper(testPaper);

        User user = userDao.find(1);
        exam.setUser(user);

        Answer answer = new Answer();
        answer.setAnswer("abc");
        answer.setUser(user);
        answer.setExam(exam);
        Question question =questionService.find(30);
        answer.setQuestion(question);

        exam.getAnswer().add(answer);

        examDao.save(exam);
    }

    @Test
    public void testFindExamByEidAndUid() throws Exception {

    }

    @Test
    public void testDel() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testQueryByUserId() throws Exception {

    }

    @Test
    public void testSelectAll() throws Exception {

    }
}