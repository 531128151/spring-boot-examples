package com.neo.codeInfo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author wupeng
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tb_sys_codeinfo_testimportexcel")
public class TbSysCodeinfoTestimportexcel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sysCodeID", type = IdType.AUTO)
    private Long sysCodeID;

    @TableField("sysCodeGUID")
    private String sysCodeGUID;

    /**
     * 编码名称
     */
    @TableField("sysCodeName")
    private String sysCodeName;

    /**
     * 编码值
     */
    @TableField("sysCodeValue")
    private String sysCodeValue;

    /**
     * 编码父节点
     */
    @TableField("sysCodeFvalue")
    private String sysCodeFvalue;

    /**
     * 编码排序
     */
    @TableField("sysCodeOrder")
    private Integer sysCodeOrder;

    /**
     * 是否有子节点
     */
    @TableField("sysCodeIsExistsChild")
    private Boolean sysCodeIsExistsChild;

    /**
     * 创建时间
     */
    @TableField("sysCodeCreateTime")
    private String sysCodeCreateTime;

    /**
     * 说明
     */
    @TableField("sysCodeRemark")
    private String sysCodeRemark;

    /**
     * 创建者所在的省级单位guid
     */
    private String creatorProvinceGuid;

    /**
     * 创建者对应的userGuid
     */
    private String creatorUserGuid;

    /**
     * 1:启用；0:禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String dateTime;

    /**
     * 缺陷所属项目，0输电，1配电，变电
     */
    private Integer type;


}
