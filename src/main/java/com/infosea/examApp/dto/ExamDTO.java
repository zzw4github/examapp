package com.infosea.examApp.dto;

/**
 * Created by infosea on 2016/5/25.
 */
public class ExamDTO {
    private String name;
    private String desc;
    private int time;

    public ExamDTO() {
    }

    public ExamDTO(String name, String desc, int time) {
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
