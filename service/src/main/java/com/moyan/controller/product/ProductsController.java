package com.moyan.controller.product;


import com.moyan.dto.ProductDTO;
import com.moyan.dto.ProductsPageQueryDTO;
import com.moyan.pojo.Products;
import com.moyan.result.PageResult;
import com.moyan.result.ResponseResult;
import com.moyan.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: IOT-Demo
 * @ClassName ProductController
 * @description:
 * @author: chen
 * @create: 2025-10-04 08:57
 **/

@RestController
@Slf4j
@Tag(name = "产品管理")
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductsService productService;

    /**
     * 产品种类分页查询
     * @param productsPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "产品种类分页查询")
    public ResponseResult<PageResult> page(ProductsPageQueryDTO productsPageQueryDTO) {
        log.info("产品种类分页查询：每页{}条-第{}页", productsPageQueryDTO.getPageSize(), productsPageQueryDTO.getPage());
        PageResult pageResult = productService.pageQuery(productsPageQueryDTO);
        return ResponseResult.success(pageResult);
    }

    /**
     * 保存产品
     *
     * @param productDTO
     * @return
     */
    @PostMapping("/saveProduct")
    @Operation(summary = "保存产品")
    public ResponseResult save(@RequestBody ProductDTO productDTO) {
        log.info("保存产品：{}", productDTO);
        productService.save(productDTO);
        return ResponseResult.success();
    }

    /**
     * 修改产品状态
     *
     * @param status
     * @param productId
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "修改产品状态")
    public ResponseResult startOrStop(@PathVariable Integer status, Long productId) {
        log.info("产品状态修改：产品ID:{},状态修改成：{}", productId, status);
        productService.startOrStop(status, productId);
        return ResponseResult.success();
    }

    /**
     * 根据产品ID查询产品信息
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    @Operation(summary = "根据产品ID查询产品信息")
    public ResponseResult<Products> getById(@PathVariable Long productId) {
        log.info("根据产品ID查询产品信息：产品ID:{}", productId);
        Products products = productService.getById(productId);
        return ResponseResult.success(products);
    }

    /**
     * 修改产品信息
     * @param productDTO
     * @return
     */
    @PutMapping
    @Operation(summary = "修改产品信息")
    public ResponseResult update(@RequestBody ProductDTO productDTO) {
        log.info("修改产品信息：{}", productDTO);
        productService.update(productDTO);
        return ResponseResult.success();
    }

    /**
     * 删除产品
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}")
    @Operation(summary = "删除产品")
    public ResponseResult delete(@PathVariable Long productId){
        log.info("删除产品：产品ID:{}", productId);
        productService.delete(productId);
        return ResponseResult.success();
    }


}
