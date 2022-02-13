package com.ginspiration.webchatui.access;

import com.ginspiration.webchatui.enumeration.WxURL;
import com.ginspiration.webchatui.util.http.HttpUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名: Token.java</br>
 * 描述: 凭证</br>
 */
@Component
@Log4j2
public class WxAccessToken {
    @Value("${wx.information.appID}")
    private String appId;
    @Value("${wx.information.appsecret}")
    private String appsecret;
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private long expiresIn;


    public String getAccessToken() {
        if (System.currentTimeMillis()>this.expiresIn) {
            log.info("access_token有效截至时间：{}，当前时间：{}",this.expiresIn,System.currentTimeMillis());
            getWxAccessToken();
        }
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn*1000+System.currentTimeMillis();
    }

    /**
     * 获取access_token
     */
    @PostConstruct
    public void getWxAccessToken() {
        // 发起GET请求获取凭证
        String requestUrl = WxURL.GET_ACCESS_TOKEN_URL.getURL().replace("APPID",appId).replace("APPSECRET",appsecret);
        JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                String access_token = jsonObject.getString("access_token");
                int expires_in = jsonObject.getInt("expires_in");
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long time = (long) expires_in*1000 + System.currentTimeMillis();
                String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
                setAccessToken(access_token);
                setExpiresIn(expires_in);
                log.info("获取到access_token:{},有效时间至:{}",access_token,sd);
            } catch (JSONException e) {
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
    }
}