package com.moyan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyan.pojo.Menu;

import java.util.List;

/**
 * @program: IOT-Demo
 * @ClassName MenuMapper
 * @description:
 * @author: chen
 * @create: 2025-09-14 12:40
 **/

public interface MenuMapper extends BaseMapper<Menu> {
    // 根据用户id查询权限
    List<String> selectPermsByUserId (Long userId);

}