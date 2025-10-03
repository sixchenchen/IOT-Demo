package com.moyan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: IOT-Demo
 * @ClassName UserPageQueryDTO
 * @description:
 * @author: chen
 * @create: 2025-10-03 11:47
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageQueryDTO {
    private String name;
    private int page;
    private int  pageSize;
}
