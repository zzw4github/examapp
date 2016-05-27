package com.infosea.examApp.service;

import com.infosea.examApp.dao.*;
import com.infosea.examApp.dto.*;
import com.infosea.examApp.pojo.*;
import com.infosea.examApp.vo.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by infosea on 2016/5/25.
 */
@Service
public class TestServiceImpl implements  TestService {
    @Autowired
    UserDao userDao;
    @Autowired
    ExamDao examDao;
    @Autowired
    TestPaperDefineDao testPaperDefineDao;
    @Autowired
    TestPaperService testPaperService;
    @Autowired
    TestPaperDao testPaperDao;
    @Autowired
    PageUtil pageUtil;
    @Autowired
    CommonDao commonDao;
    @Autowired
    AnswerDao answerDao;
    @Autowired
    QuestionDao questionDao;

    @Override
    @Transactional
    public TestDTO test(long userId, long examId) {
        User user = userDao.find(userId);
        Exam exam = examDao.findByID(examId);
        TestPaperDefine testPaperDefine =testPaperDefineDao.findById(1);
        long testPaperDefineId = testPaperDefine.getId();
        TestPaper testPaper = testPaperService.produceTestPaper(user, testPaperDefineId, exam);
        long testPaperId = testPaper.getId();
        List<Subject> subjects = pageUtil.findPageByHql(1, 1, "select testPaper.subjects from TestPaper testPaper where testPaper.id=" + testPaperId);
//        int count = commonDao.getCount("select testPaper.subjects.question.id from TestPaper testPaper where testPaper.id=" + testPaperId);
        List ids = commonDao.getIds("select  a.question_id from  subject a inner join testPaper_subject b  on a.id=b.subjects_id  where b.testPaper_id=" + testPaperId );
        //返回 试题 第一条数据
        PageBean<Subject> pageBean = new PageBean<>();
        pageBean.setCurPage(1);
        pageBean.setPageCount(1);
        pageBean.setRowsCount(ids.size());
        pageBean.setObjects(subjects);

        UserDTO userDTO = new UserDTO(user.getName(), user.getTmh(),user.getId());
        ExamDTO examDTO = new ExamDTO(exam.getName(), exam.getDesc(), exam.getTime());
        TestPaperDTO testPaperDTO = new TestPaperDTO(testPaper.getId(),testPaper.getName());
        TestDefineDTO testDefineDTO  = new TestDefineDTO();
        testDefineDTO.setTypes(testPaperDefine.getQuestionTypes());
        TestDTO testDTO = new TestDTO();
        testDTO.setUserDTO(userDTO);
        testDTO.setExamDTO(examDTO);
        testDTO.setTestPaperDTO(testPaperDTO);
        testDTO.setTestDefineDTO(testDefineDTO);
        testDTO.setPageBean(pageBean);
        testDTO.setIds(ids);
        return  testDTO;
    }

    @Transactional
    public TestDTO userTest(long userId, long examId){
        User user = userDao.find(userId);
        Exam exam = examDao.findByID(examId);
        TestPaperDefine testPaperDefine =testPaperDefineDao.findById(1);
        long testPaperDefineId = testPaperDefine.getId();
        TestDTO testDTO = new TestDTO();
        UserDTO userDTO = new UserDTO(user.getName(), user.getTmh(),user.getId());
        ExamDTO examDTO = new ExamDTO(exam.getName(), exam.getDesc(), exam.getTime());
        TestDefineDTO testDefineDTO  = new TestDefineDTO();
        testDefineDTO.setTypes(testPaperDefine.getQuestionTypes());
        testDTO.setUserDTO(userDTO);
        testDTO.setExamDTO(examDTO);
        testDTO.setTestDefineDTO(testDefineDTO);
        return  testDTO;
    }

    @Transactional
    @Override
    public ResultDTO saveAnswer(long testPaperId, long userID , Map<String,String> answerMap){
        ResultDTO resultDTO = new ResultDTO();
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        TestPaper testPaper = testPaperDao.findById(testPaperId);
        User user = userDao.find(userID);
        int totalScore = 0;

        /**
         * 获取试卷的所有试题，取得试题的正确答案，与AnswerMap中的答案比较获得分数，并把答案保存到Answer表中
         */
        for (Iterator<Subject> item = testPaper.getSubjects().iterator(); item.hasNext(); ) {
            Subject subject = item.next();
            Question question = subject.getQuestion();
            String qid = String.valueOf(question.getId());
            int score = subject.getScore();
            String stdAnswer = question.getStdAnswer();
//          保存答案
            String usrAnswer = answerMap.get(qid);
            Answer answer2 = new Answer();
            answer2.setQuestion(question);
            answer2.setAnswer(usrAnswer);
            answer2.setTestPaper(testPaper);
            answer2.setUser(user);
            QuestionDTO questionDTO= new QuestionDTO();
            questionDTO.setId(question.getId());
            if (stdAnswer.equals(usrAnswer)) {
                answer2.setTof(1);
                questionDTO.setTof(1);
                totalScore += score;
            }else {
                answer2.setTof(0);
                questionDTO.setTof(0);

            }
            questionDTO.setContent(question.getContent());
            questionDTO.setOption(question.getOption());
            questionDTO.setTrueAnswer(question.getStdAnswer());
            questionDTO.setAnswer(usrAnswer);
            questionDTO.setType(question.getType().getName());
            questionDTOs.add(questionDTO);
            answerDao.save(answer2);
        }
        //保存成绩
        Date endTime = new Date();
        long costTime = (endTime.getTime() - testPaper.getBeginTime().getTime())/1000;
        testPaper.setEndTime(new Date());
        testPaper.setCostTime(costTime);
        testPaper.setScore(totalScore);
        testPaperService.update(testPaper);
        resultDTO.setCostTime(costTime);
        resultDTO.setQuestionDTOs(questionDTOs);
        resultDTO.setScore(totalScore);
        return resultDTO;
    }
    @Transactional
    @Override
    public QuestionDTO getQuestion( long userId,  long testPaperId,  long questionId){
        QuestionDTO questionDTO = new QuestionDTO();
        Answer answer = answerDao.query(userId, testPaperId ,questionId);
        Question question = questionDao.find(questionId);
        questionDTO.setType(question.getType().getName());
        questionDTO.setId(question.getId());
        questionDTO.setTrueAnswer(question.getStdAnswer());
        questionDTO.setOption(question.getOption());
        questionDTO.setContent(question.getContent());
        questionDTO.setTof(answer.getTof());
        questionDTO.setAnswer(answer.getAnswer());
        return questionDTO;
    }
}
