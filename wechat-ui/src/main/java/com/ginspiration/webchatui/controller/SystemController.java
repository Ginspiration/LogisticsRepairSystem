package com.ginspiration.webchatui.controller;

import com.ginspiration.webchatui.access.WxAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private WxAccessToken wxAccessToken;

    @PostMapping("/getWxAccessToken")
    @ResponseBody
    public String pushAccessToken(){
        return wxAccessToken.getAccessToken();
    }
}
