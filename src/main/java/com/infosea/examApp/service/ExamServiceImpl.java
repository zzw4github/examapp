package com.infosea.examApp.service;

import com.infosea.examApp.dao.ExamDao;
import com.infosea.examApp.dao.NativeSQLDao;
import com.infosea.examApp.dao.TestPaperDao;
import com.infosea.examApp.dao.TestPaperDefineDao;
import com.infosea.examApp.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by infosea on 2016/4/20.
 */
@Service
public class ExamServiceImpl  implements ExamService{
    @Autowired
    ExamDao examDao;
    @Autowired
    NativeSQLDao nativeSQLDao;

    @Autowired
    TestPaperDao testPaperDao;
    @Autowired
    TestPaperDefineDao testPaperDefineDao;
    @Override
    @Transactional
    public Exam findByID(long id) {
       Exam exam = examDao.findByID(id);
        return exam;
    }

    @Override
    @Transactional
    public long save(Exam exam) {
        long examid =(long)examDao.save(exam);
       return  examid;
    }

    @Override
    public Exam findByEidAndUid(long eid, long uid) {
        return examDao.findByEidAndUid(eid,uid);
    }

    @Override
    public List<Exam> findAll() {
        return examDao.findAll();
    }

    @Override
    public void delete(long id) {
        Exam exam =examDao.findByID(id);
        examDao.del(exam);
    }

    @Override
    public void update(Exam exam) {
        examDao.update(exam);
    }



    @Override
    public List<Exam> findByUserId(long uid) {
        return examDao.findByUserId(uid);
    }
    @Transactional
    @Override
    public List<Exam> findALl(String hql) {
        return examDao.findAll(hql);
    }

    /**
     * 生成试卷
     * @param user
     * @param testPaperId
     * @return
     */
    public Exam produceExam(User user,long testPaperId){
        Exam exam = new Exam();
        TestPaperDefine testPaperDefine = testPaperDefineDao.findById(testPaperId);
        List<QuestionType> questionTypes = testPaperDefine.getQuestionTypes();
        String testIdStr = "";
        for(int i=0;i<questionTypes.size();i++){
            QuestionType questionType = questionTypes.get(i);
            int amount =questionType.getAmount();
            int score = questionType.getScore();
            long typeId = questionType.getType().getId();
            testIdStr += produceTestPaper(typeId,amount);
        }
        TestPaper testPaper = new TestPaper();
        testPaper.setName("测试测卷");
        testPaper.setQuestions_id(testIdStr);
        testPaper.setTestPaperDefine(testPaperDefine);
        testPaper.setUser(user);
        testPaperDao.save(testPaper);
        exam.setUser(user);
        exam.setDate(new Date());
        exam.setTestPaper(testPaper);
        examDao.save(exam);
        return exam;
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
}
