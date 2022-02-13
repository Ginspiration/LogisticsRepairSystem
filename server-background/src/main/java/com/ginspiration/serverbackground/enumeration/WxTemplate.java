package com.ginspiration.serverbackground.enumeration;

public enum WxTemplate {
    REPLY_TO_USER_MESSAGE_TEMPLATE//回复给用户消息模板
            ("<xml><ToUserName><![CDATA[TO]]></ToUserName><FromUserName><![CDATA[FROM]]></FromUserName><CreateTime>NowTime</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[MESSAGE]]></Content></xml>"),


    ;
    private final String template;
    WxTemplate(String template) {
        this.template = template;
    }
    public String getTemplate(){
        return this.template;
    }
}
