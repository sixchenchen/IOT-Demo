package com.moyan.controller.product;

import com.moyan.dto.ProductCategoryDTO;
import com.moyan.dto.ProductCategoryPageDTO;
import com.moyan.pojo.ProductCategories;
import com.moyan.result.PageResult;
import com.moyan.result.ResponseResult;
import com.moyan.service.ProductCategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: IOT-Demo
 * @ClassName ProductCatogoryController
 * @description:
 * @author: chen
 * @create: 2025-10-04 11:54
 **/

@RestController
@RequestMapping("/category")
@Slf4j
@Tag(name = "产品分类管理")
public class ProductCategoryController {

    @Autowired
    private ProductCategoriesService productCategoryService;

    /**
     * 保存产品分类
     * @param productCategoryDTO
     * @return
     */
    @PostMapping("/saveProductCategory")
    @Operation(summary = "保存产品分类")
    public ResponseResult save(@RequestBody ProductCategoryDTO productCategoryDTO){
        log.info("保存产品分类：{}",productCategoryDTO);
        productCategoryService.save(productCategoryDTO);
        return ResponseResult.success();
    }

    /**
     * 删除产品分类
     * @param categoryId
     * @return
     */
    @Operation(summary = "删除产品分类")
    @DeleteMapping("/{categoryId}")
    public ResponseResult delete(@PathVariable Long categoryId){
        log.info("删除产品分类：{}",categoryId);
        productCategoryService.delete(categoryId);
        return ResponseResult.success();
    }

    /**
     * 更新产品分类
     * @param productCategoryDTO
     * @return
     */
    @PutMapping
    @Operation(summary = "更新产品分类")
    public ResponseResult update(@RequestBody ProductCategoryDTO productCategoryDTO){
        log.info("更新产品分类：{}",productCategoryDTO);
        productCategoryService.update(productCategoryDTO);
        return ResponseResult.success();
    }



    @GetMapping("/{categoryId}")
    @Operation(summary = "查询产品分类")
    public ResponseResult<ProductCategories> getById(@PathVariable Long categoryId){
        log.info("查询产品分类：{}",categoryId);
        ProductCategories productCategories = productCategoryService.getById(categoryId);
        return ResponseResult.success(productCategories);
    }

    @GetMapping
    @Operation(summary = "查询产品分类列表")
    public ResponseResult<PageResult> page(ProductCategoryPageDTO productCategoryPageDTO){
        log.info("查询产品分类列表：{}",productCategoryPageDTO);
        PageResult pageResult = productCategoryService.page(productCategoryPageDTO);
        return ResponseResult.success(pageResult);
    }

}
