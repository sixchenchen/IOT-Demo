package com.moyan.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 产品属性表
 * @TableName product_properties
 */
@TableName(value ="product_properties")
@Data
public class ProductProperties {
    /**
     * 属性ID，自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long propertyId;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 属性名称
     */
    private String propertyName;

    /**
     * 属性标识符
     */
    private String identifier;

    /**
     * 数据类型：int,float,string,bool,enum,date
     */
    private String dataType;

    /**
     * 单位
     */
    private String unit;

    /**
     * 最小值
     */
    private String minValue;

    /**
     * 最大值
     */
    private String maxValue;

    /**
     * 步长
     */
    private String stepSize;

    /**
     * 枚举值，json格式
     */
    private String enumValues;

    /**
     * 是否只读：0-否, 1-是
     */
    private Integer isReadonly;

    /**
     * 是否必填：0-否, 1-是
     */
    private Integer isRequired;

    /**
     * 属性描述
     */
    private String description;

    /**
     * 排序号
     */
    private Integer sortOrder;

    /**
     * 记录创建时间
     */
    private Date createdAt;

    /**
     * 记录最后更新时间
     */
    private Date updatedAt;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductProperties other = (ProductProperties) that;
        return (this.getPropertyId() == null ? other.getPropertyId() == null : this.getPropertyId().equals(other.getPropertyId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getPropertyName() == null ? other.getPropertyName() == null : this.getPropertyName().equals(other.getPropertyName()))
            && (this.getIdentifier() == null ? other.getIdentifier() == null : this.getIdentifier().equals(other.getIdentifier()))
            && (this.getDataType() == null ? other.getDataType() == null : this.getDataType().equals(other.getDataType()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getMinValue() == null ? other.getMinValue() == null : this.getMinValue().equals(other.getMinValue()))
            && (this.getMaxValue() == null ? other.getMaxValue() == null : this.getMaxValue().equals(other.getMaxValue()))
            && (this.getStepSize() == null ? other.getStepSize() == null : this.getStepSize().equals(other.getStepSize()))
            && (this.getEnumValues() == null ? other.getEnumValues() == null : this.getEnumValues().equals(other.getEnumValues()))
            && (this.getIsReadonly() == null ? other.getIsReadonly() == null : this.getIsReadonly().equals(other.getIsReadonly()))
            && (this.getIsRequired() == null ? other.getIsRequired() == null : this.getIsRequired().equals(other.getIsRequired()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getSortOrder() == null ? other.getSortOrder() == null : this.getSortOrder().equals(other.getSortOrder()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPropertyId() == null) ? 0 : getPropertyId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getPropertyName() == null) ? 0 : getPropertyName().hashCode());
        result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
        result = prime * result + ((getDataType() == null) ? 0 : getDataType().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getMinValue() == null) ? 0 : getMinValue().hashCode());
        result = prime * result + ((getMaxValue() == null) ? 0 : getMaxValue().hashCode());
        result = prime * result + ((getStepSize() == null) ? 0 : getStepSize().hashCode());
        result = prime * result + ((getEnumValues() == null) ? 0 : getEnumValues().hashCode());
        result = prime * result + ((getIsReadonly() == null) ? 0 : getIsReadonly().hashCode());
        result = prime * result + ((getIsRequired() == null) ? 0 : getIsRequired().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getSortOrder() == null) ? 0 : getSortOrder().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propertyId=").append(propertyId);
        sb.append(", productId=").append(productId);
        sb.append(", propertyName=").append(propertyName);
        sb.append(", identifier=").append(identifier);
        sb.append(", dataType=").append(dataType);
        sb.append(", unit=").append(unit);
        sb.append(", minValue=").append(minValue);
        sb.append(", maxValue=").append(maxValue);
        sb.append(", stepSize=").append(stepSize);
        sb.append(", enumValues=").append(enumValues);
        sb.append(", isReadonly=").append(isReadonly);
        sb.append(", isRequired=").append(isRequired);
        sb.append(", description=").append(description);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}