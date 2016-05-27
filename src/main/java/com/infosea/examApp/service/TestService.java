package com.infosea.examApp.service;

import com.infosea.examApp.dto.QuestionDTO;
import com.infosea.examApp.dto.ResultDTO;
import com.infosea.examApp.dto.TestDTO;

import java.util.Map;

/**
 * Created by infosea on 2016/5/25.
 */
public interface TestService {
    TestDTO test(long userId, long examId);
    TestDTO userTest(long userId, long examId);
     ResultDTO saveAnswer(long testPaperId, long userID , Map<String,String> answerMap);
    QuestionDTO getQuestion(long userId, long testPaperId, long questionId);
}
