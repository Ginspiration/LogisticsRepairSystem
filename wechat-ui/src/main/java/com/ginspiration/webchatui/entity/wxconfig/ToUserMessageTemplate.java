package com.ginspiration.webchatui.entity.wxconfig;

import com.ginspiration.webchatui.enumeration.WxTemplate;
import lombok.ToString;

@ToString
public class ToUserMessageTemplate {

    private String FROM;
    private String TO;
    private String MESSAGE;

    public ToUserMessageTemplate() {
    }

    public ToUserMessageTemplate(String fromUserName, String toUserName, String message) {
        FROM = fromUserName;
        TO = toUserName;
        MESSAGE = message;
    }

    public void setFromUserName(String fromUserName) {
        FROM = fromUserName;
    }

    public void setTO(String TO) {
        this.TO = TO;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getTemplate() {
        return WxTemplate.REPLY_TO_USER_MESSAGE_TEMPLATE.getTemplate()
                .replace("FROM", this.FROM)//发送者
                .replace("TO", this.TO)//接收者
                .replace("NowTime", String.valueOf(System.currentTimeMillis()))//发送时间
                .replace("MESSAGE", this.MESSAGE);//发送内容
    }
}
