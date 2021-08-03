package com.demo.security.infrastructure.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * security 系统配置封装类
 * @author qp
 */
@Component
@ConfigurationProperties(prefix = "spring.security")
@Data
public class SecurityProperties {

    /** 不登录即可访问的接口 **/
    private String [] anonymous;

    /** 如果未认证过会走此接口包装了返回json结果给前端提示未认证 **/
    private String loginPage="/v1/sys/require";

    /** 用户名密码登录接口 UsernamePasswordAuthenticationFilter默认登录接口是/login**/
    private String loginProcessingUrl="/v1/sys/login";

    /** 退出接口地址 **/
    private String logoutUrl = "/v1/sys/logout";

    /** 放行的静态资源 **/
    private String [] statics ={
            "/doc.html",
            "/swagger-ui.html",
            "/swagger/**",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/**"};

    /** 客户端id **/
    private String clientId;

    /** 客户端密码**/
    private String clientSecret;

    /** 获取授权码地址 **/
    private String redirectUri;

}
