package com.moyan.exception;

/**
 * 密码错误异常
 */
public class PasswordMismatchException extends BaseException {

    public PasswordMismatchException() {
    }

    public PasswordMismatchException(String msg) {
        super(msg);
    }

}
