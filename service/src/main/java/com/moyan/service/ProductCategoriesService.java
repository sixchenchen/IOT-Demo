package com.moyan.service;

import com.moyan.dto.ProductCategoryDTO;
import com.moyan.dto.ProductCategoryPageDTO;
import com.moyan.pojo.ProductCategories;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moyan.result.PageResult;

/**
 * @author 16370
 * @description 针对表【product_categories(产品类别表)】的数据库操作Service
 * @createDate 2025-10-04 08:36:15
 */
public interface ProductCategoriesService extends IService<ProductCategories> {

    // 保存产品分类
    void save(ProductCategoryDTO productCategoryDTO);

    // 删除产品分类
    void delete(Long categoryId);

    // 修改产品分类
    void update(ProductCategoryDTO productCategoryDTO);

    // 分页查询产品分类
    PageResult page(ProductCategoryPageDTO productCategoryPageDTO);
}
