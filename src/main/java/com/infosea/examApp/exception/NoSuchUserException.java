package com.infosea.examApp.exception;

/**
 * Created by infosea on 2016/5/23.
 */
public class NoSuchUserException extends ExamException{
    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
