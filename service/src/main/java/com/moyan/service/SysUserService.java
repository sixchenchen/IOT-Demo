package com.moyan.service;

import com.moyan.dto.SysUserDTO;
import com.moyan.dto.UserPageQueryDTO;
import com.moyan.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moyan.result.PageResult;

/**
* @author 16370
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2025-09-14 09:59:37
*/
public interface SysUserService extends IService<SysUser> {
    //新增用户
    void save(SysUserDTO sysUserDTO);

    //分页查询
    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    // 启用禁用员工
    void startOrStop(String status, Long id);

    // 编辑员工信息
    void update(SysUserDTO sysUserDTO);
}
