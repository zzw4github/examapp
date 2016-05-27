package com.infosea.examApp.dto;

import com.infosea.examApp.pojo.Subject;
import com.infosea.examApp.pojo.TestPaper;
import com.infosea.examApp.vo.PageBean;

import java.util.List;

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
    private long remainTime = 0 ;
    private String tMinute;
    private String tSecond;
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

    public String gettMinute() {
        return tMinute;
    }

    public void settMinute(String tMinute) {
        this.tMinute = tMinute;
    }

    public String gettSecond() {
        return tSecond;
    }

    public void settSecond(String tSecond) {
        this.tSecond = tSecond;
    }
}
