package com.moyan.dto;

import lombok.Data;

/**
 * @program: IOT-Demo
 * @ClassName ProductPageQueryDTO
 * @description:
 * @author: chen
 * @create: 2025-10-04 09:00
 **/

@Data
public class ProductsPageQueryDTO {
    private Long currentId;
    private String productName;
    private String categoryName;
    private Integer status;
    private int page;
    private int  pageSize;
}
