package com.moyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.moyan.constant.StatusConstant;
import com.moyan.context.BaseContext;
import com.moyan.dto.ProductDTO;
import com.moyan.dto.ProductsPageQueryDTO;
import com.moyan.pojo.Products;
import com.moyan.result.PageResult;
import com.moyan.service.ProductsService;
import com.moyan.mapper.ProductsMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 16370
 * @description 针对表【products(产品表)】的数据库操作Service实现
 * @createDate 2025-10-04 08:36:15
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products>
        implements ProductsService {

    @Autowired
    private ProductsMapper productsMapper;

    /**
     * 分页查询
     *
     * @param productsPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(ProductsPageQueryDTO productsPageQueryDTO) {
        productsPageQueryDTO.setCurrentId(BaseContext.getCurrentId());
        PageHelper.startPage(productsPageQueryDTO.getPage(), productsPageQueryDTO.getPageSize());
        Page<Products> page = productsMapper.pageQuery(productsPageQueryDTO);
        long total = page.getTotal();
        List<Products> result = page.getResult();
        return new PageResult(total, result);
    }

    /**
     * 插入产品
     * @param productDTO
     */
    @Override
    public void save(ProductDTO productDTO) {
        Products products = new Products();
        BeanUtils.copyProperties(productDTO, products);
        products.setStatus(StatusConstant.ENABLE);
        // TODO 产品唯一标志根据产品类型来定义
        products.setProductKey(UUID.randomUUID().toString());
        productsMapper.insert(products);
    }

    /**
     * 修改产品状态
     *
     * @param status
     * @param productId
     */
    @Override
    public void startOrStop(Integer status, Long productId) {
        Products products = Products.builder()
                .productId(productId)
                .status(status).build();
        productsMapper.update(products);
    }

    /**
     * 修改产品信息
     *
     * @param productDTO
     */
    @Override
    public void update(ProductDTO productDTO) {
        Products products = new Products();
        BeanUtils.copyProperties(productDTO, products);
        productsMapper.update(products);
    }

    /**
     * 删除产品
     *
     * @param productId
     */
    @Override
    public void delete(Long productId) {
        productsMapper.deleteById(productId);
    }
}




