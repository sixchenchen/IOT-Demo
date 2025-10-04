package com.moyan.service;

import com.moyan.dto.ProductDTO;
import com.moyan.dto.ProductsPageQueryDTO;
import com.moyan.pojo.Products;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moyan.result.PageResult;

/**
* @author 16370
* @description 针对表【products(产品表)】的数据库操作Service
* @createDate 2025-10-04 08:36:15
*/
public interface ProductsService extends IService<Products> {

    // 分页查询
    PageResult pageQuery(ProductsPageQueryDTO productsPageQueryDTO);
    // 添加产品
    void save(ProductDTO productDTO);
    // 修改产品状态
    void startOrStop(Integer status, Long productId);
    // 修改产品
    void update(ProductDTO productDTO);

    void delete(Long productId);
}
