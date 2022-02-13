package com.ginspiration.webchatui.config.wxconfig;

import com.ginspiration.webchatui.access.WxAccessToken;
import com.ginspiration.webchatui.entity.wxconfig.WxVerifyMessage;
import com.ginspiration.webchatui.enumeration.WxURL;
import com.ginspiration.webchatui.util.ReadFileUtil;
import com.ginspiration.webchatui.util.WxUtil;
import com.ginspiration.webchatui.util.http.HttpUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Objects;


@Controller
@Log4j2
@DependsOn("wxAccessToken")
public class WxInitialize {

    @Value("${wx.information.appID}")
    private String appId;
    @Value("${wx.information.appsecret}")
    private String appsecret;

    @Value("${wx.information.authorizedScope}")
    private String authorizedScope;
    @Value("${wx.information.TOKEN}")
    private String TOKEN;
    @Value("${server.serverAddress}")
    private String server_address;

    @Autowired
    private WxAccessToken wxAccessToken;


    /**
     * 验证Token
     * @param wxVerifyMessage 微信返回的消息
     * @param response 响应体
     */
    @GetMapping("/")
    public void configInterface(WxVerifyMessage wxVerifyMessage, HttpServletResponse response) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        log.info("正在进行认证....");
        String[] message = new String[]
                {
                        this.TOKEN,
                        wxVerifyMessage.getNonce(),
                        wxVerifyMessage.getTimestamp()
                };
        Arrays.sort(message);
        boolean v = WxUtil.verifyInterfaceConfigInfo(wxVerifyMessage.getSignature(), message[0] + message[1] + message[2]);
        try {
            if (v) {
                PrintWriter writer = response.getWriter();
                writer.print(wxVerifyMessage.getEchostr());//验证成功返回随机字符串
                log.info("认证成功!");
            }
        } catch (IOException e) {
            log.info("认证失败!");
            e.printStackTrace();
        }
    }

    /**
     * 创建微信公众菜单
     */
    @PostConstruct
    public void createWxMenu() {
        String requestUrl = WxURL.CREATE_MENU_URL.getURL().replace("ACCESS_TOKEN", wxAccessToken.getAccessToken());//创建菜单的地址
        String menu = Objects.requireNonNull(ReadFileUtil.readJsonFile("classpath:jsonTemplate/menu.json"))//读取菜单内容
                .replace("SERVER_ADDRESS",server_address)//配置服务器地址
                .replace("APPID",appId)
                .replace("SCOPE",authorizedScope)
                ;
        log.info("公众号菜单：{}",menu);
        JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "POST", menu);
        log.info("公众号菜单创建状态：{}",jsonObject);
    }
}
