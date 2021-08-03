package com.demo.security.interfaces.facade;

import com.demo.security.infrastructure.base.JsonResult;
import com.demo.security.infrastructure.exception.CustomerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@RestController
@Slf4j
@Api(tags = "登录认证")
@RequestMapping("/v1/sys/")
public class SystemController {

    @PostMapping("login")
    @ApiOperation(value = "用户名密码登录", notes = "该方法不会执行 由security过滤器执行 此方法只是对外提供地址 ")
    public void signIn(
            @ApiParam(value = "用户名", name = "username", required = true)
            @RequestParam(value = "username", required = true)
            @NotBlank(message = "用户名不能为空")
                    String username,
            @ApiParam(value = "密码", name = "password", required = true)
            @RequestParam(value = "password", required = true)
            @NotBlank(message = "密码不能为空")
                    String password, HttpServletRequest request) {
    }

    @ApiOperation(value = "当需要身份认真时候跳转到这里", notes = "如果有异常状态码是401")
    @GetMapping("require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public JsonResult<String> require(HttpServletRequest request, HttpServletResponse response) throws IOException, CustomerException {
        String targetUrl = request.getRequestURI();
        log.info("ip:" +request.getRemoteAddr());
        log.info("引发跳转的请求是:"+targetUrl);
        String remoteAddr = request.getRemoteAddr();
        log.info("访问得ip地址:"+remoteAddr);
        return JsonResult.error("需要身份认证通过");
    }

    @ApiOperation(value = "获取用户登录信息", notes = "获取用户登录信息")
    @PostMapping("getAuthentication")
    public JsonResult<Authentication> getAuthenticationByWeb() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return JsonResult.success(authentication);
    }

    @PostMapping("logout")
    @ApiOperation(value = "退出", notes = "退出")
    public void signOut() {
        log.info("调用security退出过滤器");
    }

}
