package com.moyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyan.pojo.ProductProperties;
import com.moyan.service.ProductPropertiesService;
import com.moyan.mapper.ProductPropertiesMapper;
import org.springframework.stereotype.Service;

/**
* @author 16370
* @description 针对表【product_properties(产品属性表)】的数据库操作Service实现
* @createDate 2025-10-04 08:36:15
*/
@Service
public class ProductPropertiesServiceImpl extends ServiceImpl<ProductPropertiesMapper, ProductProperties>
    implements ProductPropertiesService{

}




