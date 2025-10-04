package com.moyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.moyan.constant.ProductCategoryConstants;
import com.moyan.dto.ProductCategoryDTO;
import com.moyan.dto.ProductCategoryPageDTO;
import com.moyan.exception.ProductNotEmptyException;
import com.moyan.mapper.ProductCategoriesMapper;
import com.moyan.mapper.ProductsMapper;
import com.moyan.pojo.ProductCategories;
import com.moyan.pojo.Products;
import com.moyan.result.PageResult;
import com.moyan.service.ProductCategoriesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 16370
 * @description 针对表【product_categories(产品类别表)】的数据库操作Service实现
 * @createDate 2025-10-04 08:36:15
 */
@Service
public class ProductCategoriesServiceImpl extends ServiceImpl<ProductCategoriesMapper, ProductCategories>
        implements ProductCategoriesService {

    @Autowired
    private ProductCategoriesMapper productCategoriesMapper;

    @Autowired
    private ProductsMapper productsMapper;

    /**
     * 保存产品分类
     *
     * @param productCategoryDTO
     */
    @Override
    public void save(ProductCategoryDTO productCategoryDTO) {
        ProductCategories productCategories = new ProductCategories();
        BeanUtils.copyProperties(productCategoryDTO, productCategories);
        // TODO 父类别ID,排序号
        productCategories.setParentId(ProductCategoryConstants.PARENT_ID);
        productCategories.setSortOrder(ProductCategoryConstants.SORT_ORDER);
        productCategoriesMapper.insert(productCategories);
    }

    /**
     * 删除产品分类
     *
     * @param categoryId
     */
    @Override
    public void delete(Long categoryId) {
        // 查询分类下的产品
        List<Products> productsList = productCategoriesMapper.queryProductById(categoryId);
        // 分类下产品不为空
        if (productsList != null && !productsList.isEmpty()) {
            throw new ProductNotEmptyException("分类下存在产品，不能删除");
        } else {
            // 分类下产品为空
            productCategoriesMapper.deleteById(categoryId);
        }
    }

    /**
     * 修改产品分类
     * @param productCategoryDTO
     */
    @Override
    public void update(ProductCategoryDTO productCategoryDTO) {
        ProductCategories productCategories = new ProductCategories();
        BeanUtils.copyProperties(productCategoryDTO, productCategories);
        productCategoriesMapper.update(productCategories);
    }

    /**
     * 分页查询产品分类
     * @param productCategoryPageDTO
     * @return
     */
    @Override
    public PageResult page(ProductCategoryPageDTO productCategoryPageDTO) {
        PageHelper.startPage(productCategoryPageDTO.getPage(), productCategoryPageDTO.getPageSize());
        Page<ProductCategories> page = productCategoriesMapper.pageQuery(productCategoryPageDTO);
        long total = page.getTotal();
        List<ProductCategories> result = page.getResult();
        return new PageResult(total, result);
    }
}




