package com.demo.security.interfaces.facade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qp
 * @DATE: 2021/8/18 17:10
 */
@RestController
@Api(tags = "服务状态")
public class StatusController {

    @GetMapping("/status")
    @ApiOperation(value = "获取系统服务状态", notes = "获取系统服务状态")
    public String getStatus(){
        return "ok";
    }
}
