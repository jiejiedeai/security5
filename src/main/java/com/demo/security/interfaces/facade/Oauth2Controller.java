package com.demo.security.interfaces.facade;

import com.alibaba.fastjson.JSON;
import com.demo.security.domain.vo.AccessToken;
import com.demo.security.infrastructure.base.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/oauth2/")
@Api(tags = "oauth功能模块")
@Slf4j
public class Oauth2Controller {

    @Value("${server.port}")
    private Integer port;

    @Value("${server.address}")
    private String address;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("getAuthorizeCode")
    @ApiOperation(value = "获取授权码", notes = "获取授权码",hidden = true)
    public JsonResult<String> getAuthorizeCode(
            @ApiParam(value = "授权码", name = "code", required = true)
            @RequestParam(value = "code", required = true)
            @NotBlank(message = "授权码不能为空")
                    String code){
        return JsonResult.success(code);
    }


    @PostMapping("getCode")
    @ApiOperation(value = "获取授权码", notes = "获取授权码")
    public JsonResult<String> getCode(
            @ApiParam(value = "客户端id", name = "clientId", required = true)
            @RequestParam(value = "clientId", required = true)
            @NotBlank(message = "客户端id不能为空")
                    String clientId,
            @ApiParam(value = "客户端秘钥", name = "clientSecret", required = true)
            @RequestParam(value = "clientSecret", required = true)
            @NotBlank(message = "客户端秘钥不能为空")
                    String clientSecret,
            HttpServletRequest request) {
        StringBuffer tokenUrl= new StringBuffer();
        tokenUrl.append("http://")
                .append(address)
                .append(":")
                .append(port)
                .append("/oauth/authorize?client_id=")
                .append(clientId)
                .append("&response_type=code&redirect_url=")
                .append("http://")
                .append(address)
                .append(":")
                .append(port)
                .append("/oauth2/getAuthorizeCode");
        String basicAuth = "Basic %s";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format(basicAuth, Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes())));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return JsonResult.success(tokenUrl.toString());
    }

    @PostMapping("getToken")
    @ApiOperation(value = "根据授权码获取token", notes = "根据授权码获取token")
    public JsonResult<AccessToken> getToken(
            @ApiParam(value = "授权码", name = "code", required = true)
            @RequestParam(value = "code", required = true)
            @NotBlank(message = "授权码不能为空")
            String code,
            @ApiParam(value = "客户端id", name = "clientId", required = true)
            @RequestParam(value = "clientId", required = true)
            @NotBlank(message = "客户端id不能为空")
            String clientId,
            @ApiParam(value = "客户端秘钥", name = "clientSecret", required = true)
            @RequestParam(value = "clientSecret", required = true)
            @NotBlank(message = "客户端秘钥不能为空")
            String clientSecret,
            HttpServletRequest request) {
        StringBuffer tokenUrl= new StringBuffer();
        tokenUrl.append("http://")
                .append(address)
                .append(":")
                .append(port)
                .append("/oauth/token?grant_type=authorization_code&code=")
                .append(code)
                .append("&redirect_uri=")
                .append("http://")
                .append(address)
                .append(":")
                .append(port)
                .append("/oauth2/getAuthorizeCode");
        String basicAuth = "Basic %s";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format(basicAuth, Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes())));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        try {
            String result = restTemplate.postForObject(tokenUrl.toString(), httpEntity, String.class);
            AccessToken accessToken = JSON.parseObject(result, AccessToken.class);
            return JsonResult.success(accessToken);
        }catch (Exception e){
            log.error("token error:"+e.getLocalizedMessage());
            return JsonResult.error("获取token失败");
        }
    }

}
