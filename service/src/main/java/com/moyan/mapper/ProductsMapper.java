package com.moyan.mapper;

import com.github.pagehelper.Page;
import com.moyan.annotation.AutoFill;
import com.moyan.dto.ProductsPageQueryDTO;
import com.moyan.enumeration.OperationType;
import com.moyan.pojo.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
* @author 16370
* @description 针对表【products(产品表)】的数据库操作Mapper
* @createDate 2025-10-04 08:36:15
* @Entity com.moyan.pojo.Products
*/
public interface ProductsMapper extends BaseMapper<Products> {

    Page<Products> pageQuery(ProductsPageQueryDTO productsPageQueryDTO);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into products(product_name,products.product_key,category_id,description,manufacturer,model,image_url,create_time,update_time,create_by,update_by) values(#{productName},#{productKey},#{categoryId},#{description},#{manufacturer},#{model},#{imageUrl},#{createTime},#{updateTime},#{createBy},#{updateBy})")
    int insert(Products products);


    @AutoFill(OperationType.UPDATE)
    void update(Products products);

}




