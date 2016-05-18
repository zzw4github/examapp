package com.infosea.examApp.util;

import com.infosea.examApp.pojo.Option;
import com.infosea.examApp.pojo.Question;

import com.infosea.examApp.service.OptionService;
import com.infosea.examApp.service.QuestionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by infosea on 2016/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/root-context.xml"}) //指定Spring的配置文件 /为classpath下
// //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class DocImport {
    @Resource
    private OptionService optionService;
    @Resource
    private QuestionService questionService;

    @Before //在每个测试用例方法之前都会执行
    public void init() {
    }

    @After //在每个测试用例执行完之后执行
    public void destory() {
    }

    @Test
      //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
    @Rollback(false) //这里设置为false，就让事务不回滚
    public void updateQuestion() throws Exception {

        List<Question> list = questionService.findAll();
        for (int i = 0; i < list.size(); i++) {
            Question question = list.get(i);
            String q = question.getContent();
            long id = question.getId();
            q = q.trim();
            question.setContent(q);
            questionService.save(question);

        }
    }

    public static void find(String s) {
        Pattern pattern = Pattern.compile("\\d、");
        pattern.matcher(s);
    }

    @Test
      //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
    @Rollback(false) //这里设置为false，就让事务不回滚
    public void updateOption() throws Exception {
        Pattern pattern = Pattern.compile("[A-Z]、");
        List<Option> list = optionService.findAll();
        for (int i = 0; i < list.size(); i++) {
            Option option = list.get(i);
            String value = option.getValue();
            StringBuffer values = new StringBuffer();
            Matcher matcher = pattern.matcher(value);
            while (matcher.find()) {
                String mstr = matcher.group();
                matcher = matcher.appendReplacement(values, " " + mstr);
            }
            matcher.appendTail(values);
            option.setValue(values.toString().trim());
            optionService.save(option);

        }
    }
}