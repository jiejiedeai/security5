package com.demo.security.interfaces.facade;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.security.domain.model.User;
import com.demo.security.domain.service.UserService;
import com.demo.security.infrastructure.base.JsonResult;
import com.demo.security.interfaces.dto.CommonPageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/test/")
@RestController
@Api(tags = "测试模块")
public class TestController {

    @Autowired
    private UserService userService;

    @PostMapping("searchUserPage")
    @ApiOperation(value = "查询用户分页列表")
    public JsonResult<IPage<User>> searchUserPage(
            @RequestBody
            @Validated
            CommonPageDTO commonPageDTO, BindingResult bindingResult){
        return userService.searchUserPage(commonPageDTO);
    }

    @PostMapping("searchUserList")
    @ApiOperation(value = "查询用户列表")
    public JsonResult<List<User>> searchUserList(
            @ApiParam(name = "searcKey",value = "关键词")
            @RequestParam(value = "searchKey",required = false)
            String searchKey){
        return userService.searchUserList(searchKey);
    }
}
