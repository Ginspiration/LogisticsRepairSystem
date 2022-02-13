package com.ginspiration.webchatui.util;

import com.ginspiration.webchatui.enumeration.WxURL;
import com.ginspiration.webchatui.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

@Slf4j
public class WxUtil {

    public static String getOpenId(HttpServletRequest request,String appId,String appsecret){
        String code = request.getParameter("code");
        String requestUrl = WxURL.WEB_ACCESS_TOKEN_GET_URL.getURL()
                .replace("APPID",appId)
                .replace("SECRET",appsecret)
                .replace("CODE",code)
                ;
        JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "POST", null);
        log.info("获取到微信网页授权信息:{}",jsonObject);
        return (String) jsonObject.get("openid");
    }

    public static boolean verifyInterfaceConfigInfo(String source, String res) {
        return SHA1(res).compareTo(source) == 0;
    }

    /**
     * sha1加密算法
     * @param str
     * @return
     */
    public static String SHA1(String str){
        if(str == null || str.length()==0){
            return null;
        }
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for(int i=0;i<j;i++){
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
