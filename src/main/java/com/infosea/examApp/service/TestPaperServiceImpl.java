package com.infosea.examApp.service;

import com.infosea.examApp.dao.NativeSQLDao;
import com.infosea.examApp.dao.QuestionDao;
import com.infosea.examApp.dao.TestPaperDao;
import com.infosea.examApp.dao.TestPaperDefineDao;
import com.infosea.examApp.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * Created by infosea on 2016/4/25.
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {
    @Autowired
    TestPaperDao testPaperDao;
    @Autowired
    NativeSQLDao nativeSQLDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    TestPaperDefineDao testPaperDefineDao;
    @Autowired
    AnswerService answerService;




    @Override
    @Transactional
    public TestPaper findById(long id) {
        return testPaperDao.findById(id);
    }

    @Override
    public List<TestPaper> findAll() {
        return testPaperDao.findAll();
    }

    @Override
    public Serializable save(TestPaper testPaper) {
        return testPaperDao.save(testPaper);
    }

    @Override
    public TestPaper findByIdAndUid(long eid, long uid) {
        return testPaperDao.findByIdAndUid(eid, uid);
    }

    @Override
    public void del(TestPaper testPaper) {
        testPaperDao.del(testPaper);
    }

    @Override
    public void update(TestPaper testPaper) {
        testPaperDao.update(testPaper);
    }

    /**
     * 生成试卷
     * @param user
     * @param testPaperId
     * @return
     */
    @Override
    public TestPaper produceTestPaper(User user, long testPaperId, Exam exam){
        TestPaperDefine testPaperDefine = testPaperDefineDao.findById(testPaperId);
        List<QuestionType> questionTypes = testPaperDefine.getQuestionTypes();
        List<Subject> subjects = new ArrayList<>();
        for(int i=0;i<questionTypes.size();i++){
            QuestionType questionType = questionTypes.get(i);
            int amount =questionType.getAmount();
            int score = questionType.getScore();
            long typeId = questionType.getType().getId();
            subjects.addAll(produceSubjects(typeId,amount,score));
        }
        TestPaper testPaper = new TestPaper();
        testPaper.setName("测试测卷");
        testPaper.setTestPaperDefine(testPaperDefine);
        testPaper.setUser(user);
        testPaper.setExam(exam);
        testPaper.setSubjects(subjects);
        testPaperDao.save(testPaper);
        return testPaper;
    }

    /*
  *@description 根据试题id范围 随机生成生成指定书目的试题id
  * @param begin 试题起始ID
  * @param end   试题结束ID
  * @param size  生成试题ID的书目
  * @pageSize    将试题ID分成多少部分 每个部分取对应的个数
   */
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
            remainPage = random.nextInt(pageCount - 1) + 1;  // 不从最后一页取，因为最后一页 id数比其他页少

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

    public String produceTestPaper(long typeId,int account)  {
        List<Integer> questionIdList = nativeSQLDao.getIds("select id from question where type_id="+typeId+" order by id asc");
        String questionIdStr = produceRandomNumberString(questionIdList.get(0), questionIdList.get(questionIdList.size() - 1), account);
        return  questionIdStr;
    }

    public List<Subject> produceRandomSubjects(int begin, int end, int size, int pageSize ,int score) {
        /*
        begin id起始  81
        end   id结束  115
        pageSize 每页id 5
        size 需要的id数。  10
          */

        Set<Integer> idSet = new HashSet<>();
        List<Subject> subjects = new ArrayList<>();
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
            remainPage = random.nextInt(pageCount - 1) + 1;  // 不从最后一页取，因为最后一页 id数比其他页少

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
                    Question question = questionDao.find(l);
                    Subject subject = new Subject();
                    subject.setQuestion(question);
                    subject.setScore(score);
                    subjects.add(subject);
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
        return subjects;
    }



    public List<Subject> produceSubjects(int begin, int end, int count, int score) {
        return produceRandomSubjects(begin, end, count, 5,score);
    }

    public List<Subject> produceSubjects(long typeId,int account,int score)  {
        List<Integer> questionIdList = nativeSQLDao.getIds("select id from question where type_id="+typeId+" order by id asc");
        List<Subject> subjects = produceSubjects(questionIdList.get(0), questionIdList.get(questionIdList.size() - 1), account,score);
        return  subjects;
    }


}
