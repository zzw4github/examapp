package com.infosea.examApp.exception;

/**
 * Created by infosea on 2016/5/23.
 */
public class NotFindException extends ExamException {
    private String  mesage ="没有找到对应的对象" ;

    public NotFindException(String message) {
        super(message);
    }

    public NotFindException(String message, Throwable cause) {
        super(message, cause);
    }
}
