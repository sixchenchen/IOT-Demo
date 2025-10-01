package com.moyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyan.constant.PasswordConstant;
import com.moyan.constant.StatusConstant;
import com.moyan.constant.UserTypeConstant;
import com.moyan.context.BaseContext;
import com.moyan.dto.UserDTO;
import com.moyan.mapper.SysUserMapper;
import com.moyan.pojo.SysUser;
import com.moyan.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author 16370
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2025-09-14 09:59:37
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 新增用户
     * @param userDTO
     */
    @Override
    public void save(UserDTO userDTO) {
        SysUser sysUser = new SysUser();
        // 对象属性拷贝
        BeanUtils.copyProperties(userDTO,sysUser);
        // 设置账号状态，默认状态0表示正常，1表示停用
        sysUser.setStatus(StatusConstant.ENABLE);
        // 设置用户类型,默认普通用户
        sysUser.setType(UserTypeConstant.DEFAULT_USER);
        // 设置密码，默认密码
        sysUser.setPassword(passwordEncoder.encode(PasswordConstant.DEFAULT_PASSWORD));
        // 设置标志，默认未删除
        sysUser.setDelFlag(StatusConstant.NOT_DELETE);
        // 设置当前记录创建用户事件
        sysUser.setCreateTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());
        // 设置当前创建用户的id修改的id
        if (BaseContext.getCurrentId()!=null){
            sysUser.setCreateBy(BaseContext.getCurrentId());
            sysUser.setUpdateBy(BaseContext.getCurrentId());
        }else {
            sysUser.setCreateBy(-1L);
            sysUser.setUpdateBy(-1L);
        }
        sysUserMapper.insert(sysUser);
    }
}




