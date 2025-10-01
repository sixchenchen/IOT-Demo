package com.moyan.exception;

/**
 * 密码错误异常
 */
public class UserExistsException extends BaseException {

    public UserExistsException() {
    }

    public UserExistsException(String msg) {
        super(msg);
    }

}
