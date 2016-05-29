package com.infosea.examApp.dto;

/**
 * Created by infosea on 2016/5/25.
 */
public class TestPaperDTO {
    private long id;
    private String name;
    private long beginTime;
    private long endTime;
    private long maxEndTime;
    public TestPaperDTO() {
    }

    public TestPaperDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getMaxEndTime() {
        return maxEndTime;
    }

    public void setMaxEndTime(long maxEndTime) {
        this.maxEndTime = maxEndTime;
    }

    @Override
    public String toString() {
        return "TestPaperDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", maxEndTime=" + maxEndTime +
                '}';
    }
}
