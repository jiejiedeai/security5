package com.demo.security.infrastructure.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.demo.security.infrastructure.base.JsonResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 */
@Component
@Slf4j
public class CustomerAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * @param request
     * @param response
     * @param authentication Authentication 也是security 的一个核心接口 作用是封装我们的用户信息
     *                       认证信息包括 请求id、session是什么、认证通过后自定义的UserDetails等
     * @throws IOException
     * @throws ServletException
     */
    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        log.info("登录成功");
        response.setContentType("application/json;charset=utf-8");
        JsonResult<String> success = JsonResult.success("登录成功");
        response.getWriter().write(JSONObject.toJSONString(success));
        response.setStatus(200);
    }
}

