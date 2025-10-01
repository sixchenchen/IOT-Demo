package com.moyan.dto;

import lombok.Data;

/**
 * @program: IOT-Demo
 * @ClassName UserDTO
 * @description:
 * @author: chen
 * @create: 2025-09-30 22:43
 **/

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String nickName;
    private String type;
    private String status;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
}
