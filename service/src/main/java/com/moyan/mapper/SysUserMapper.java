package com.moyan.mapper;

import com.moyan.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 16370
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2025-09-14 09:59:37
* @Entity com.moyan.pojo.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 插入用户
     *
     * @return
     */
    @Insert("insert into sys_user(user_name,nick_name,password,type,status,email,phonenumber,sex,avatar,create_by,create_time,update_by,update_time,del_flag) "
            +"values" +
            "(#{userName},#{nickName},#{password},#{type},#{status},#{email},#{phonenumber},#{sex},#{avatar},#{createBy},#{createTime},#{updateBy},#{updateTime},#{delFlag})")
    int insert(SysUser user);
}




