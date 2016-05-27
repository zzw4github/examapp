package com.infosea.examApp.exception;

/**
 * Created by infosea on 2016/5/23.
 */
public class DataAccessException extends ExamException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
