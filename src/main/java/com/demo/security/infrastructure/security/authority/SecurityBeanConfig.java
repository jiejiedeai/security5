package com.demo.security.infrastructure.security.authority;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDecisionManager;

@Configuration
public class SecurityBeanConfig {
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new OneVotePermit();
    }

    @Bean
    public DynamicSecurityInterceptor dynamicSecurityInterceptor(RedisTemplate redisTemplate) {
        return new DynamicSecurityInterceptor(urlRedisSecurityMetadataSource(redisTemplate), accessDecisionManager());
    }

    @Bean
    public UrlRedisSecurityMetadataSource urlRedisSecurityMetadataSource(RedisTemplate redisTemplate) {
        UrlRedisSecurityMetadataSource source = new UrlRedisSecurityMetadataSource(redisTemplate);
        return source;
    }
}
