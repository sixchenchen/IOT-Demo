package com.moyan.mapper;

import com.github.pagehelper.Page;
import com.moyan.annotation.AutoFill;
import com.moyan.dto.ProductCategoryPageDTO;
import com.moyan.enumeration.OperationType;
import com.moyan.pojo.ProductCategories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyan.pojo.Products;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
* @author 16370
* @description 针对表【product_categories(产品类别表)】的数据库操作Mapper
* @createDate 2025-10-04 08:36:15
* @Entity com.moyan.pojo.ProductCategories
*/
public interface ProductCategoriesMapper extends BaseMapper<ProductCategories> {

    @Insert("insert into product_categories (parent_id,category_name,description,icon_url,sort_order,create_time,update_time,create_by,update_by) " +
            "values" +
            " (#{parentId},#{categoryName},#{description},#{iconUrl},#{sortOrder},#{createTime},#{updateTime},#{createBy},#{updateBy})")
    @AutoFill(OperationType.INSERT)
    int insert(ProductCategories productCategories);

    // 通过产品分类id查询产品
    List<Products> queryProductById(Long categoryId);

    // 修改产品分类
    @AutoFill(OperationType.UPDATE)
    void update(ProductCategories productCategories);

    // 分页查询产品分类
    Page<ProductCategories> pageQuery(ProductCategoryPageDTO productCategoryPageDTO);
}




