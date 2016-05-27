package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.vo.PageBean;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by infosea on 2016/5/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class ExamDaoImplTest {
    @Resource
    ExamDao examDao;

    @Resource
    UserDao userDao;
    @Test
    public void testFindByID() throws Exception {
       Exam exam = examDao.findByID(1);
        assertNotNull(exam);
    }

    @Test
    public void testSave() throws Exception {
        Exam exam = new Exam();
        User user = userDao.find(2L);
//        exam.setUser(user);
        exam.setDate(new Date());
        exam.setValidFlag("YES");
        examDao.save(exam);
    }

    @Test
    public void testFindByEidAndUid() throws Exception {
        Exam exam = examDao.findByEidAndUid(1L,2L);
//        assertEquals(exam.getUser().getId(),2L);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Exam> examList = examDao.findAll();
        assertEquals(examList.size(),1);
    }

    @Test
    public void testDel() throws Exception {

    }

    @Test
    @Transactional
    @Rollback(false) //默认回滚
    public void testUpdate() throws Exception {
        Exam exam = examDao.findByID(1L);
//        exam.setScore(100);
        examDao.update(exam);
//        assertEquals(100,exam.getScore());

    }

    @Test
    public void testFindByUserId() throws Exception {
      assertNotNull(examDao.findByUserId(2));;
    }

    @Test
    public void testFindAll1() throws Exception {

    }

    @Test
    public void testDelByEid() throws Exception {

    }

    @Test
    public void testDelByUid() throws Exception {

    }

    @Test
    public void testDelByEidUid() throws Exception {

    }

    @Test
    public void testFind() throws Exception {
        Map map = new HashMap<String , String>();
        map.put("validFlag","Y");
//        PageBean<Exam> exams =examDao.find(1,1,map);
//        assertEquals(exams.getObjects().size(),1);
    }

    @Test
    public void testFindExam() throws Exception {
        Map map = new HashMap<String , String>();
        map.put("validFlag","Y");
//        List<Exam> exams =examDao.findExam(1,1,map);
//        assertEquals(exams.size(),1);
    }

    @Test
    public void testGetCounts() throws Exception {

    }
}