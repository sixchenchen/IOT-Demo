package com.moyan.service;

import com.moyan.dto.UserDTO;
import com.moyan.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 16370
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2025-09-14 09:59:37
*/
public interface SysUserService extends IService<SysUser> {
    //新增用户
    void save(UserDTO userDTO);
}
