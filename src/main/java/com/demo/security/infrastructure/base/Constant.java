package com.demo.security.infrastructure.base;

public class Constant {

    /** 授权码对应token key 前缀 **/
    public static final String AUTHORIZE_TOKEN_KEY_PREFIX="authorize_token_prefix:";

    /** 权限资源KEY **/
    public static final String RESOURCE_KEY="RESOURCE_KEY:";

    /** 用户授权成功后 JWT token **/
    public static final String JWT_TOKEN_PREFIX="JWT_TOKEN:";

    /** 过滤器编码 **/
    public static final String FILTER_ENCODING="UTF-8";

    /** security 的中文提示认证 **/
    public static final String MESSAGE_ZH_CN="classpath:org/springframework/security/messages_zh_CN";
}
