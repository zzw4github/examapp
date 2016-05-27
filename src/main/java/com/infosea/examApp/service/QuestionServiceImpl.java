package com.infosea.examApp.service;

import com.infosea.examApp.dao.QuestionDao;
import com.infosea.examApp.dao.QuestionTypeDao;
import com.infosea.examApp.pojo.Option;
import com.infosea.examApp.pojo.Question;
import com.infosea.examApp.pojo.QuestionType;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.util.POI;
import com.infosea.examApp.util.RegularEx;
import com.infosea.examApp.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuestionTypeDao questionTypeDao;
    @Autowired
    POI poi;
    @Autowired
    RegularEx regularEx;
    @Override
    public long save(Question question) {
        return(long) questionDao.save(question);
    }

    @Override
    @Transactional
    public Question find(long id) {
        return questionDao.find(id);
    }

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public List<Question> findByHQL(String hql){
        return questionDao.findByHQL(hql);
    }

    @Override
    public void update(Question question) {
        questionDao.update(question);
    }

    @Override
    public List<Question> find(int pageCount, int curPage, String qids) {
        String hql = "select q from Question q left join fetch q.type left join fetch q.option where q.id in (" + qids + ") order by q.id asc";
        List<Question> questionPageBean =  questionDao.find(pageCount,curPage,hql);
        return questionPageBean;
    }
    @Override
    public List<Question> find(int pageCount, int curPage, Map<String,String> map) {
        List<Question> questionPageBean =  questionDao.find(pageCount,curPage,map);
        return questionPageBean;
    }
    @Override
//    new File("湖南人文科技学院图书馆新生入馆教育考试题库.doc")

    @Rollback(true)
    /*
    @description 将 doc解析后存放到数据库 question 表中
     */
    public void saveQuestionFromFile(File file) {
        int count = 0;
        Map<String, List<String>> questionMap = null;
        try {
            questionMap = poi.readByExtractor(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, List<String>> entry : questionMap.entrySet()) {
            String key = entry.getKey();
            List<String> val = entry.getValue();
            for (String str : val) {
                str = str.replace("(", "（").replace(")", "）");

                Question question = new Question();
                Map<String, String> map = regularEx.getQuestionFromUniformStringByRegex(str);
                if (map.size() == 0) {
                    continue;
                } else {
                    QuestionType questionType = null;
                    if (key.equals("chooseMore")) {
                        questionType = questionTypeDao.find(3l);
                    } else if (key.equals("choose")) {
                        questionType = questionTypeDao.find(2l);
                    } else if (key.equals("check")) {
                        questionType = questionTypeDao.find(1l);
                    }
                    Option option = new Option();
                    question.setStdAnswer(map.get("answer").trim());
                    question.setContent(map.get("question"));
                    question.setDesc("desc");
                    question.setDate(new Date());
                    option.setValue(map.get("option"));
//                    question.setOption(option);
//                    questionDao.save(question);
                }
            }
        }
    }
}
