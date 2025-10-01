package com.moyan.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    /**
     * 总记录数
     */
    private Integer total;

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> result = new ResponseResult<T>();
        result.code = 200;
        return result;
    }
    public static <T> ResponseResult<T> success(String msg) {
        ResponseResult<T> result = new ResponseResult<T>();
        result.msg = msg;
        result.code = 200;
        return result;
    }

    public static <T> ResponseResult<T> success(T object) {
        ResponseResult<T> result = new ResponseResult<T>();
        result.data = object;
        result.code = 200;
        return result;
    }


    public static <T> ResponseResult<T> success(String msg,T object) {
        ResponseResult<T> result = new ResponseResult<T>();
        result.data = object;
        result.msg = msg;
        result.code = 200;
        return result;
    }
    public static <T> ResponseResult<T> success(Integer code,String msg,T object) {
        ResponseResult<T> result = new ResponseResult<T>();
        result.data = object;
        result.msg = msg;
        result.code = code;
        return result;
    }

    public static <T> ResponseResult<T> error(String msg) {
        ResponseResult result = new ResponseResult();
        result.msg = msg;
        result.code = 400;
        return result;
    }
    public static <T> ResponseResult<T> error(Integer code,String msg) {
        ResponseResult result = new ResponseResult();
        result.msg = msg;
        result.code = code;
        return result;
    }
}