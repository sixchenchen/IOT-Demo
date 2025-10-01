package com.moyan.handler;


import com.moyan.constant.MessageConstant;
import com.moyan.exception.BaseException;
import com.moyan.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ResponseResult exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return ResponseResult.error(ex.getMessage());
    }

    /**
     * 捕获sql异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ResponseResult exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        // Duplicate entry 'zhangsan' for key 'employee.idx_username'
        String message = ex.getMessage();
        // 判断异常信息中是否包含"Duplicate entry"
        if (message.contains("Duplicate entry")) {
            // 将异常信息按空格分割
            String[] split = message.split(" ");
            // 获取重复的username
            String username = split[2];
            // 构造错误信息
            String msg = username + MessageConstant.ALREADY_EXISTS;
            // 返回错误结果
            return ResponseResult.error(msg);
        } else {
            // 返回未知错误结果
            return ResponseResult.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
