package com.infosea.examApp.enums;

/**
 * Created by infosea on 2016/5/18.
 * 使用枚举表示常量字段
 */
public enum ExamEnum {
    ADMIN(1,"管理员"),
    NORMAL(2,"学生");

    private int state;
    private String stateInfo;

     ExamEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ExamEnum stateOf(int index){
        for(ExamEnum state : values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
