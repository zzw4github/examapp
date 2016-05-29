package com.infosea.examApp.dto;

import com.infosea.examApp.pojo.Subject;
import com.infosea.examApp.pojo.TestPaper;
import com.infosea.examApp.vo.PageBean;

import java.util.*;

/**
 * Created by infosea on 2016/5/24.
 */
public class TestDTO {
    private UserDTO userDTO;
    private ExamDTO examDTO;
    private TestPaperDTO testPaperDTO;
    private TestDefineDTO testDefineDTO;
    private PageBean<Subject> pageBean;
    private List<Integer> ids ;
    private Set<String> answeredIds ;
    private long remainTime = 0 ;
    private long nowTime ;
    private long maxEndTIme;
    private boolean success;
    private Map<String,String> answerMap = new HashMap<>();

    public TestDTO() {
    }

    public TestDTO(boolean success, long nowTime) {
        this.success = success;
        this.nowTime = nowTime;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ExamDTO getExamDTO() {
        return examDTO;
    }

    public void setExamDTO(ExamDTO examDTO) {
        this.examDTO = examDTO;
    }

    public TestDefineDTO getTestDefineDTO() {
        return testDefineDTO;
    }

    public void setTestDefineDTO(TestDefineDTO testDefineDTO) {
        this.testDefineDTO = testDefineDTO;
    }

    public PageBean<Subject> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<Subject> pageBean) {
        this.pageBean = pageBean;
    }

    public TestPaperDTO getTestPaperDTO() {
        return testPaperDTO;
    }

    public void setTestPaperDTO(TestPaperDTO testPaperDTO) {
        this.testPaperDTO = testPaperDTO;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public long getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(long remainTime) {
        this.remainTime = remainTime;
    }


    public Set<String> getAnsweredIds() {
        return answeredIds;
    }

    public void setAnsweredIds(Set<String> answeredIds) {
        this.answeredIds = answeredIds;
    }

    public Map<String, String> getAnswerMap() {
        return answerMap;
    }

    public void setAnswerMap(Map<String, String> answerMap) {
        this.answerMap = answerMap;
    }



    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getMaxEndTIme() {
        return maxEndTIme;
    }

    public void setMaxEndTIme(long maxEndTIme) {
        this.maxEndTIme = maxEndTIme;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "userDTO=" + userDTO +
                ", examDTO=" + examDTO +
                ", testPaperDTO=" + testPaperDTO +
                ", testDefineDTO=" + testDefineDTO +
                ", pageBean=" + pageBean +
                ", ids=" + ids +
                ", answeredIds=" + answeredIds +
                ", remainTime=" + remainTime +
                ", nowTime=" + nowTime +
                ", maxEndTIme=" + maxEndTIme +
                ", success=" + success +
                ", answerMap=" + answerMap +
                '}';
    }
}
