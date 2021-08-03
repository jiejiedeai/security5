package com.demo.security.interfaces.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "通用分页DTO")
public class CommonPageDTO implements Serializable {

    private static final long serialVersionUID = -3171815441707732006L;

    @ApiModelProperty(value = "搜索关键词")
    private String searchKey;

    @ApiModelProperty(value = "当前页数")
    @NotNull(message = "{page.not.exists}")
    @Range(min = 1,message = "{page.range}")
    private Integer page;

    @ApiModelProperty(value = "每页条数")
    @NotNull(message = "{size.not.exists}")
    @Range(min = 1,message = "{size.range}")
    private Integer size;

}
