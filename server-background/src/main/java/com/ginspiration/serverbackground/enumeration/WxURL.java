package com.ginspiration.serverbackground.enumeration;

public enum WxURL {
    GET_ACCESS_TOKEN_URL//微信access_token获取地址
            ("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"),
    CREATE_MENU_URL//创建菜单地址
            ("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"),
    WEB_AUTHORIZED_ADDRESS//微信网页授权地址
            ("https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"),
    ADD_TEMPORARY_MATERIAL//新增临时素材
            ("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"),
    ADD_PERMANENT_MATERIAL//新增永久图片素材
            ("https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN"),
    GROUP_SENDING_ACCORDING_LABEL_URL//根据标签进行群发
            ("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN"),
    MESSAGE_PREVIEW_INTERFACE_URL//消息预览接口
            ("https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN"),
    UPLOAD_IMG_AND_ARTICLES_URL//上传图文消息
            ("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN")
    ;
    private final String URL;

    WxURL(String url) {
        this.URL = url;
    }

    public String getURL() {
        return this.URL;
    }
}
