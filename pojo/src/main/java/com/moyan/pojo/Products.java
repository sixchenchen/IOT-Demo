package com.moyan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品表
 * @TableName products
 */
@TableName(value ="products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    /**
     * 产品ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品唯一标识
     */
    private String productKey;

    /**
     * 类别ID
     */
    private Long categoryId;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 制造商
     */
    private String manufacturer;

    /**
     * 型号
     */
    private String model;

    /**
     * 通信协议类型
     */
    private String protocolType;

    /**
     * 网络类型
     */
    private String networkType;

    /**
     * 数据格式
     */
    private String dataFormat;

    /**
     * 产品状态：0-下线, 1-上线
     */
    private Integer status;

    /**
     * 产品图片URL
     */
    private String imageUrl;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新人ID
     */
    private Long updateBy;
}