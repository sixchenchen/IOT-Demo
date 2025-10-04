package com.moyan.dto;

import lombok.Data;

/**
 * @program: IOT-Demo
 * @ClassName ProductCategoryDTO
 * @description:
 * @author: chen
 * @create: 2025-10-04 11:57
 **/

@Data
public class ProductCategoryDTO {
    private Long categoryId;
    private String categoryName;
    private String description;
    private String iconUrl;
}
