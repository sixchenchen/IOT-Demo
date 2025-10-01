package com.moyan.controller;

import com.moyan.result.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: IOT-Demo
 * @ClassName IndexController
 * @description:
 * @author: chen
 * @create: 2025-09-13 18:34
 **/

@RestController
@RequestMapping("index")
public class IndexController {
    @GetMapping("hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public ResponseResult<String> hello(){

        return ResponseResult.success("hello iot");
    }
}
