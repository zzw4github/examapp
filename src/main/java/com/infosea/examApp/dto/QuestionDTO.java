package com.infosea.examApp.dto;

/**
 * Created by infosea on 2016/5/26.
 */
public class QuestionDTO {
    private long id;
    private Integer tof;
    private String trueAnswer;
    private String answer;
    private String content;
    private String option;
    private  String type;
    private long nextId;
    private long preId;
    private long userId;

    public QuestionDTO() {
    }

    public QuestionDTO(long id, Integer tof) {
        this.id = id;
        this.tof = tof;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getTof() {
        return tof;
    }

    public void setTof(Integer tof) {
        this.tof = tof;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getNextId() {
        return nextId;
    }

    public void setNextId(long nextId) {
        this.nextId = nextId;
    }

    public long getPreId() {
        return preId;
    }

    public void setPreId(long preId) {
        this.preId = preId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
