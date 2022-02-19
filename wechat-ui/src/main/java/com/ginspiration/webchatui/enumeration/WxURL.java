package com.ginspiration.webchatui.enumeration;

public enum WxURL {
    GET_ACCESS_TOKEN_URL//微信access_token获取地址
            ("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"),
    CREATE_MENU_URL//创建菜单地址
            ("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"),
    WEB_AUTHORIZED_ADDRESS//微信网页授权地址
            ("https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"),
    ADD_TEMPORARY_MATERIAL//新增临时素材
            ("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"),
    WEB_ACCESS_TOKEN_GET_URL
            (" https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"),
    SEND_REPAIRED_TO_USER
            ("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN")
    ;
    private final String URL;
    WxURL(String url) {
        this.URL = url;
    }
    public String getURL(){
        return this.URL;
    }
}
