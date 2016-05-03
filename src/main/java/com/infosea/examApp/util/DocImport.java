package com.infosea.examApp.util;

import com.infosea.examApp.dao.NativeSQLDao;
import com.infosea.examApp.dao.QuestionDao;
import com.infosea.examApp.dao.QuestionTypeDao;
import com.infosea.examApp.dao.TestPaperDao;
import com.infosea.examApp.pojo.Option;
import com.infosea.examApp.pojo.Question;

import com.infosea.examApp.pojo.QuestionType;
import com.infosea.examApp.pojo.TestPaper;
import com.infosea.examApp.service.OptionService;
import com.infosea.examApp.service.QuestionService;
import org.hibernate.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by infosea on 2016/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/root-context.xml"}) //指定Spring的配置文件 /为classpath下
//@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class DocImport {
    //  @Autowired
//  private ApplicationContext appplicationContext; //自动注入applicationContext，这样就可以使用appli*.getBean("beanName")
    @Resource       //指定name属性就是byname注入，指定type属性就是bytype注入
    private QuestionDao questionDao;
    @Resource
    private QuestionTypeDao questionTypeDao;

    @Resource
    private NativeSQLDao nativeSQLDao;
    @Resource
    private TestPaperDao testPaperDao;
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
    @Transactional  //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
    @Rollback(false) //这里设置为false，就让事务不回滚
    public void testSave() throws Exception {
        POI poi = new POI();
        int count = 0;
        RegularEx regularEx = new RegularEx();
        Map<String, List<String>> questionMap = poi.testReadByExtractor(new File("湖南人文科技学院图书馆新生入馆教育考试题库.doc"));
        for (Map.Entry<String, List<String>> entry : questionMap.entrySet()) {
            String key = entry.getKey();
            List<String> val = entry.getValue();
            if (key.equals("choosemore")) {
                for (String str : val) {
                    str = str.replace("(", "（").replace(")", "）");
                    Question question = new Question();
                    System.out.println(str);
                    if (regularEx.parseString(str)) {
                        Map<String, String> map = regularEx.getQuestionFromRegex(str);
                        if (map.size() == 0) {
                            System.out.println("------" + str);
                            continue;
                        }
//多选第5题 21题没有匹配到
                        else {
                            System.out.println(map.get("answer"));
                            System.out.println(map.get("question"));
                            System.out.println(map.get("option"));

//                            QuestionType questionType =questionTypeDao.find(3l);
//                            Option option = new Option();
//                            question.setStdAnswer(map.get("answer").trim());
//                            question.setQuestion(map.get("question"));
//                            question.setDesc("desc");
//                            question.setDate(new Date());
//                            question.setQuestionType(questionType);
//                            option.setValue(map.get("option"));
//                            question.setOption(option);
//                            questionDao.save(question);
                        }
                    }

                }
            }
        }
    }

    public String produceRandomNumberString(int begin, int end, int size, int pageSize) {
        /*
        begin id起始  81
        end   id结束  115
        pageSize 每页id 5
        size 需要的id数。  10
          */

        Set<Integer> idSet = new HashSet<>();
        StringBuffer stringBuffer = new StringBuffer();
        int pageCount = 0; //页数
        int addSuccess = 1;
        int totalCount = end - begin + 1; //总记录数 70
        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize; // 总页数 14
        } else {
            pageCount = totalCount / pageSize + 1;
        }

        int commonRealPageSize = size / pageCount;
        int remainCount = 0;
        int remainPage = 0;

        Random random = new Random();
        if (size % pageCount != 0) {
            remainCount = size % pageCount; //每页取30/14 个 2*14 =28  剩余要取的id数  2 ；
//          remainPage = random.nextInt(pageCount) + 1;  //从哪一页取剩余没取的书目 ，也就是 那一页多 3 个 id
            remainPage = random.nextInt(pageCount-1)+1;  // 不从最后一页取，因为最后一页 id数比其他页少

        }
        for (int j = 1; j <= pageCount; j++) { //pageCount 14
            int begin1 = begin + (j - 1) * pageSize; // j=14时   pageSize = 5 begin=11 begin1 = 13*5+11 = 76
            int end1 = begin + j * pageSize > end ? end : begin + j * pageSize;                   // j=14时   pageSize = 5  begin=11 end1 = 151
            int b = 0;
            for (int i = 0; ; i++) {
                int l = random.nextInt(end1 - begin1) + begin1;
                idSet.add(l);                      // i=0 idset.size =1
                if (idSet.size() == addSuccess) {
                    b++;                                   //1
                    addSuccess++;                          //addsSuccess =2
                    stringBuffer.append(l).append(",");
                    if (b == commonRealPageSize && j != remainPage) {
                        break;
                    }
                    if (b > commonRealPageSize && b < commonRealPageSize + remainCount && j == remainPage) {

                    }
                    if (b == commonRealPageSize + remainCount) {
                        break;
                    }
                } else {

                }

            }
        }
        return stringBuffer.toString();
    }

    public String produceRandomNumberString(int begin, int end, int count) {
        return produceRandomNumberString(begin, end, count, 5);
    }

    @Test
    @Transactional  //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
    @Rollback(false) //这里设置为false，就让事务不回滚
    public void produceTestPaper() throws Exception {
        List<Integer> chooseIdList = nativeSQLDao.getIds("select id from question where questiontype_id=2 order by id asc");
        List<Integer> chooseMoreIdList = nativeSQLDao.getIds("select id from question where questiontype_id=3 order by id asc");
        List<Integer> checkIdList = nativeSQLDao.getIds("select id from question where questiontype_id=1 order by id asc");
        int j = 0;
        String chooseIdStr = "";
        String chooseMoreStr = "";
        String checkIdStr = "";
        for (int i = 0; i < 10000; i++) {
            chooseIdStr = produceRandomNumberString(chooseIdList.get(0), chooseIdList.get(chooseIdList.size() - 1), 30);
            chooseMoreStr = produceRandomNumberString(chooseMoreIdList.get(0), chooseMoreIdList.get(chooseMoreIdList.size() - 1), 10);
            checkIdStr = produceRandomNumberString(checkIdList.get(0), checkIdList.get(checkIdList.size() - 1), 10);
            TestPaper testPaper = new TestPaper();
            testPaper.setName("试卷 ");//+NumberFormat.formatInteger(++j)
            testPaper.setSglc_ids(chooseIdStr);
            testPaper.setMulc_ids(chooseMoreStr);
            testPaper.setTof_ids(checkIdStr);
            testPaperDao.save(testPaper);
            if (i % 20 == 0)
                testPaperDao.flush();
        }
        testPaperDao.flush();
//        testPaperDao.findAllTestPaper();
    }

    @Test
    @Transactional  //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
    @Rollback(false) //这里设置为false，就让事务不回滚
    public void updateQuestion() throws Exception {

        List<Question> list = questionService.findAll();
        for (int i = 0; i < list.size(); i++) {
            Question question = list.get(i);
            String q = question.getQuestion();
            long id = question.getId();
            q = q.trim();
            question.setQuestion(q);
            questionService.save(question);

        }
    }

    public static void find(String s) {
        Pattern pattern = Pattern.compile("\\d、");
        pattern.matcher(s);
    }

    @Test
    @Transactional  //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
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