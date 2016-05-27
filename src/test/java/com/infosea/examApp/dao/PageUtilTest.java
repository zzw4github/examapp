package com.infosea.examApp.dao;

import com.infosea.examApp.pojo.Question;
import com.infosea.examApp.pojo.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by infosea on 2016/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class PageUtilTest {
    @Resource
    PageUtil<Subject> pageUtil;
    @Test
    public void testFindPageByHql() throws Exception {
        String hql = "select testPaper.subjects from TestPaper testPaper where testPaper.id=5";
        List<Subject> questions = pageUtil.findPageByHql(1, 1, hql);
        System.out.println(questions.get(0).getQuestion().getContent());
    }
}