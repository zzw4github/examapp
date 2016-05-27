package com.infosea.examApp.dto;

/**
 * Created by infosea on 2016/5/27.
 */
public class PageDTO {
    private Long questionId;
    private String questionAnswer;
    private Integer tMinute;
    private Integer tSecond;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public Integer gettMinute() {
        return tMinute;
    }

    public void settMinute(Integer tMinute) {
        this.tMinute = tMinute;
    }

    public Integer gettSecond() {
        return tSecond;
    }

    public void settSecond(Integer tSecond) {
        this.tSecond = tSecond;
    }
}
