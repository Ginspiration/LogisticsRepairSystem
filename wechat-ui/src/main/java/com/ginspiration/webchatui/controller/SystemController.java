package com.ginspiration.webchatui.controller;

import com.ginspiration.webchatui.access.WxAccessToken;
import com.ginspiration.webchatui.entity.RepairedToUser;
import com.ginspiration.webchatui.service.IRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private WxAccessToken wxAccessToken;

    @Autowired
    private IRepairService repairService;

    @PostMapping("/getWxAccessToken")
    @ResponseBody
    public String pushAccessToken(){
        return wxAccessToken.getAccessToken();
    }

    /**
     * 发送信息到指定用户
     * @param repairedToUser
     * @return
     */
    @PostMapping("/sendRepairedToUser")
    @ResponseBody
    public String sendRepairedToUser(@RequestBody RepairedToUser repairedToUser){
        return repairService.sendRepairedToUser(repairedToUser);
    }
}
