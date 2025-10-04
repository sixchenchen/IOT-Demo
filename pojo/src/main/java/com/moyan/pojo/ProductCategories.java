package com.moyan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 产品类别表
 * @TableName product_categories
 */
@TableName(value ="product_categories")
@Data
public class ProductCategories {
    /**
     * 类别ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long categoryId;

    /**
     * 父类别ID
     */
    private Integer parentId;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 类别描述
     */
    private String description;

    /**
     * 图标URL
     */
    private String iconUrl;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 记录创建人
     */
    private Long createBy;

    /**
     * 记录修改人
     */
    private Long updateBy;
}