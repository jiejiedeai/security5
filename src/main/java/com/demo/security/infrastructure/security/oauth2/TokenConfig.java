package com.demo.security.infrastructure.security.oauth2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * oauth token 配置管理
 * JWT 非对称加密
 * 公钥与私钥是一对  私钥加密 公钥解密
 * 使用jdk 工具生成秘钥
 * cmd 窗口使用keytool
 * keytool -genkeypair -alias oauth2 -keyalg RSA -keypass oauth2 -keystore oauth2.jks
 * 将oauth2.jks 复制到项目中
 */
@Configuration
public class TokenConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //生成签名的key,这里使用对称加密
        jwtAccessTokenConverter.setSigningKey("test");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore(){
        //jwt 管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

}
