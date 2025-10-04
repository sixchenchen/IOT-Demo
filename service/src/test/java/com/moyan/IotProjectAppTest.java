package com.moyan;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moyan.mapper.ProductCategoriesMapper;
import com.moyan.mapper.ProductPropertiesMapper;
import com.moyan.mapper.ProductsMapper;
import com.moyan.pojo.ProductCategories;
import com.moyan.pojo.ProductProperties;
import com.moyan.pojo.Products;
import com.moyan.pojo.SysUser;
import com.moyan.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple IotProjectApp.
 */
@SpringBootTest(classes = IotProjectApp.class)
public class IotProjectAppTest{

    @Autowired
    private ProductsMapper productsMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ProductCategoriesMapper productCategoriesMapper;
    @Autowired
    private ProductPropertiesMapper productPropertiesMapper;
    @Test
    public void GetUser(){
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, "admin123");
        SysUser user = sysUserMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test
    public void GetProducts(){
        List<String> list = Arrays.asList("1","2","3");
        QueryWrapper wrapper = new QueryWrapper<>().in("product_id",list);
        List<Products> products = productsMapper.selectList(wrapper);
        System.out.println(products);
    }
    @Test
    public void GetProductCategory(){
        QueryWrapper<ProductCategories> wrapper = new QueryWrapper<>();
        wrapper.in("category_id", Arrays.asList("1","2","3"));
        List<ProductCategories> list = productCategoriesMapper.selectList(wrapper);
        System.out.println(list);
    }
    @Test
    public void GetProductProperty(){
        QueryWrapper<ProductProperties> wrapper = new QueryWrapper<>();
        wrapper.in("property_id", Arrays.asList("1","2","3"));
        List<ProductProperties> list = productPropertiesMapper.selectList(wrapper);
        System.out.println(list);
    }
}
