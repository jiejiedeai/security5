package com.demo.security.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author qp
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Resource对象", description="")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "适配类型 0.全部 1.app 2.web")
    private Integer adapterType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "是否h5")
    private Integer ish5;

    @ApiModelProperty(value = "是否展示 0.不展示 1.展示")
    private Integer isShow;

    @ApiModelProperty(value = "上级菜单id 顶级菜单parentId是0")
    private Integer parentId;

    @ApiModelProperty(value = "资源说明")
    private String remark;

    @ApiModelProperty(value = "资源编码")
    private String resourceCode;

    @ApiModelProperty(value = "资源图片地址")
    private String resourceImageUrl;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源名称(前端展示用)")
    private String resourceNameShow;

    @ApiModelProperty(value = "资源类型 0.通用 1.平台 2.一级指标 3.二级指标 4.菜单 5.功能快 6.按钮")
    private Integer resourceType;

    @ApiModelProperty(value = "资源路径")
    private String resourceUrl;

    @ApiModelProperty(value = "资源状态 0.删除 1.正常")
    private Integer status;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "组件名称")
    private String componentName;

    @ApiModelProperty(value = "icon")
    private String icon;

    @ApiModelProperty(value = "路由地址")
    private String routingAddress;

    @ApiModelProperty(value = "标题")
    private String title;


}
