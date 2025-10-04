package com.moyan.exception;

/**
 * @program: IOT-Demo
 * @ClassName ProductNotEmptyException
 * @description:
 * @author: chen
 * @create: 2025-10-04 14:26
 **/

public class ProductNotEmptyException extends BaseException{
    public ProductNotEmptyException() {
    }
    public ProductNotEmptyException(String msg) {
        super(msg);
    }
}
