package com.infosea.examApp.exception;

/**
 * Created by infosea on 2016/5/16.
 */
public class ExamException extends RuntimeException {

    public ExamException(String message) {
        super(message);
    }

    public ExamException(String message, Throwable cause) {
        super(message, cause);
    }
}
