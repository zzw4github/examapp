package com.infosea.examApp.exception;

/**
 * Created by infosea on 2016/5/23.
 */
public class BusinessException extends ExamException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
