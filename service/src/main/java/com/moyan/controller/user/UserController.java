package com.moyan.controller.user;

import com.moyan.dto.SysUserDTO;
import com.moyan.dto.UserPageQueryDTO;
import com.moyan.pojo.SysUser;
import com.moyan.result.PageResult;
import com.moyan.result.ResponseResult;
import com.moyan.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @program: IOT-Demo
 * @ClassName UserController
 * @description:
 * @author: chen
 * @create: 2025-09-30 22:08
 **/

@Slf4j
@Tag(name = "用户管理接口", description = "提供用户相关的REST API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUserService userService;
    /**
     * 新增用户
     * @param sysUserDTO
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "新增用户",
            description = "这是详细的接口说明")
    public ResponseResult<String> save(@RequestBody SysUserDTO sysUserDTO) {
        log.info("新增用户：{}",sysUserDTO);
        userService.save(sysUserDTO);
        return ResponseResult.success();
    }

    /**
     * 用户分页查询
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "用户分页查询",description = "根据条件分页查询用户列表")
    public ResponseResult<PageResult> page(UserPageQueryDTO userPageQueryDTO){
        log.info("用户分页查询：{}",userPageQueryDTO);
        PageResult pageResult = userService.pageQuery(userPageQueryDTO);
        return ResponseResult.success(pageResult);
    }

    /**
     * 启用禁用用户
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "启用或禁用用户")
    public ResponseResult startOrStop(@PathVariable Integer status,Long id){
        log.info("启用禁用用户：{}，{}",status,id);
        userService.startOrStop(status,id);
        return ResponseResult.success();
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询用户")
    public ResponseResult<SysUser> getById(@PathVariable Long id) {
        log.info("查询用户：{}",id);
        SysUser user = userService.getById(id);
        user.setPassword("******");
        return ResponseResult.success(user);
    }

    @PutMapping
    @Operation(summary = "更新用户")
    public ResponseResult update(@RequestBody SysUserDTO sysUserDTO){
        log.info("更新用户：{}",sysUserDTO);
        userService.update(sysUserDTO);
        return ResponseResult.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public ResponseResult delete(@PathVariable Long id,Integer delFlag){
        log.info("删除用户：{}",id);
        userService.delete(id,delFlag);
        return ResponseResult.success();
    }
}
