package com.moyan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: IOT-Demo
 * @ClassName ProductCategoryPageDTO
 * @description:
 * @author: chen
 * @create: 2025-10-04 14:59
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryPageDTO {
    private String categoryName;
    private int page;
    private int  pageSize;
}
