package com.moyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.moyan.constant.PasswordConstant;
import com.moyan.constant.StatusConstant;
import com.moyan.constant.UserTypeConstant;
import com.moyan.context.BaseContext;
import com.moyan.dto.SysUserDTO;
import com.moyan.dto.UserPageQueryDTO;
import com.moyan.mapper.SysUserMapper;
import com.moyan.pojo.SysUser;
import com.moyan.result.PageResult;
import com.moyan.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 16370
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2025-09-14 09:59:37
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 新增用户
     *
     * @param sysUserDTO
     */
    @Override
    public void save(SysUserDTO sysUserDTO) {
        SysUser sysUser = new SysUser();
        // 对象属性拷贝
        BeanUtils.copyProperties(sysUserDTO, sysUser);
        // 设置账号状态，默认状态0表示正常，1表示停用
        sysUser.setStatus(StatusConstant.ENABLE);
        // 设置用户类型,默认普通用户
        sysUser.setType(UserTypeConstant.DEFAULT_USER);
        // 设置密码，默认密码
        sysUser.setPassword(passwordEncoder.encode(PasswordConstant.DEFAULT_PASSWORD));
        // 设置标志，默认未删除
        sysUser.setDelFlag(StatusConstant.NOT_DELETE);
        sysUserMapper.insert(sysUser);
    }

    /**
     * 分页查询
     *
     * @param userPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        //select * from employee limit 0,10
        //开始分页查询
        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getPageSize());

        Page<SysUser> page = sysUserMapper.pageQuery(userPageQueryDTO);
        long total = page.getTotal();
        List<SysUser> records = page.getResult();
        return new PageResult(total, records);
    }

    /**
     * 启用禁用员工
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        SysUser user = SysUser.builder()
                .status(status)
                .id(id).build();
        sysUserMapper.update(user);
    }

    /**
     * 编辑员工信息
     *
     * @param sysUserDTO
     */
    @Override
    public void update(SysUserDTO sysUserDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO, sysUser);
        sysUserMapper.update(sysUser);
    }

    /**
     * 删除员工
     *
     * @param id
     */
    @Override
    public void delete(Long id, Integer delFlag) {
        // 修改员工删除标记
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setDelFlag(delFlag);
        sysUserMapper.update(sysUser);
    }
}




