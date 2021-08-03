package com.demo.security.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AccessToken对象", description="授权对象")
public class AccessToken {

    @ApiModelProperty(value = "授权token")
    private String access_token;

    @ApiModelProperty(value = "token类型")
    private String token_type;

    @ApiModelProperty(value = "刷新token")
    private String refresh_token;

    @ApiModelProperty(value = "过期时间")
    private int expires_in;

    @ApiModelProperty(value = "访问范围")
    private String scope;

    @ApiModelProperty(value = "jti")
    private String jti;

}
