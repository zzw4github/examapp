package com.infosea.examApp.service;

import com.infosea.examApp.dao.UserDao;
import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by infosea on 2016/5/19.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class ExamServiceImplTest {
    @Resource
    ExamService examService;
    @Resource
    UserDao userDao;

    /**
     * 生成一次考试，并生成一张试卷
     * @throws Exception
     */
    @Test
    public void testProduceExam() throws Exception {
        User user = userDao.find(2L);
        long testPaperDefineId=1L;
       Exam exam = examService.produceExam(user,testPaperDefineId);


    }

}