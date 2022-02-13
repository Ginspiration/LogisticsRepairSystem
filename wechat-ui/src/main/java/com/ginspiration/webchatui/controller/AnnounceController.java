package com.ginspiration.webchatui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公告
 */
@Controller
@RequestMapping("/announce")
public class AnnounceController {
    /**
     * 公告页面
     * @return
     */
    @GetMapping("/history")
    public String history(){
        return "announce";
    }
    @PostMapping("/publish")
    @ResponseBody
    public String publish(String content){
        return null;
    }
}
