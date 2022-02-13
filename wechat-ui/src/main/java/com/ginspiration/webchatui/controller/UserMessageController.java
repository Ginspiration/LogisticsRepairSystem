package com.ginspiration.webchatui.controller;

import com.ginspiration.webchatui.access.WxAccessToken;
import com.ginspiration.webchatui.entity.wxconfig.ToUserMessageTemplate;
import com.ginspiration.webchatui.util.http.HttpUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

@Controller
@Log4j2
public class UserMessageController {

    @Autowired
    private WxAccessToken wxAccessToken;

    /**
     * 处理用户发送过来的消息
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/")
    @ResponseBody
    public String testPath2(HttpServletRequest request) throws IOException {
        Map<String, String> map = HttpUtil.parseMessage(request.getInputStream());
        String userOpenId = map.get("FromUserName");
        log.info("接收到用户信息:{}",map);
        ToUserMessageTemplate toUserMessageTemplate = new ToUserMessageTemplate();
        toUserMessageTemplate.setFromUserName(map.get("ToUserName"));
        toUserMessageTemplate.setTO(userOpenId);
        toUserMessageTemplate.setMESSAGE("欢迎关注!!");

        return toUserMessageTemplate.getTemplate();
    }
}
