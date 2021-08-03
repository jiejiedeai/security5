package com.demo.security.infrastructure.base;

/**
 * @Author: qp
 * @DATE: 2021/4/22 11:58
 */
public enum MessageEnum implements MessageInfo {

    SUCCESS_OPERATOR("success.operator"),
    SUCCESS_ADD("success.add"),
    SUCCESS_UPDATE("success.update"),
    SUCCESS_DELETE("success.delete");

    private String messageKey;

    MessageEnum(String messageKey){
        this.messageKey = messageKey;
    }

    @Override
    public String getMessage() {
        return messageKey;
    }
}
