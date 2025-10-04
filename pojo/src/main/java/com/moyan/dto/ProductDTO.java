package com.moyan.dto;

import lombok.Data;

/**
 * @program: IOT-Demo
 * @ClassName ProductDTO
 * @description: 产品传输对象
 * @author: chen
 * @create: 2025-10-04 10:46
 **/

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private Long categoryId;
    private String description;
    private String manufacturer;
    private String model;
    private String imageUrl;
    private String createAt;
    private String updateAt;
    private String createBy;
    private String updateBy;
}
