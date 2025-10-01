package com.moyan.controller.user;

import com.moyan.dto.UserDTO;
import com.moyan.result.ResponseResult;
import com.moyan.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: IOT-Demo
 * @ClassName UserController
 * @description:
 * @author: chen
 * @create: 2025-09-30 22:08
 **/

@RestController("/user")
@Slf4j
@Tag(name = "用户管理接口", description = "提供用户相关的REST API")

public class UserController {
    @Autowired
    private SysUserService userService;
    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "新增用户",
            description = "这是详细的接口说明")
    public ResponseResult<String> save(@RequestBody UserDTO userDTO) {
        log.info("新增用户：{}",userDTO);
        userService.save(userDTO);
        return ResponseResult.success();
    }
}
