package com.demo.security.infrastructure.security.handler;

import com.demo.security.infrastructure.base.Constant;
import com.demo.security.infrastructure.base.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录处理器
 */
@Component
@Slf4j
public class CustomerLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            //获取请求中的accesstoken
            String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(StringUtils.isNotBlank(accessToken)){
                if (authentication != null) {
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    String username = userDetails.getUsername();
                    log.info(username + "退出成功");
                }
                //退出后 清除对应的token在rendis中内容
                redisTemplate.delete(Constant.JWT_TOKEN_PREFIX+accessToken);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(JsonResult.success("退出成功")));
            }else{
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(objectMapper.writeValueAsString(JsonResult.success("token 失效")));
            }
        }catch (Exception e){
            log.info("退出失败");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.success("token 失效")));
        }
    }
}
