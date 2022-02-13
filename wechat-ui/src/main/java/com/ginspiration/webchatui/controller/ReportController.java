package com.ginspiration.webchatui.controller;

import com.ginspiration.webchatui.entity.UserReport;
import com.ginspiration.webchatui.entity.common.Report;
import com.ginspiration.webchatui.enumeration.WxURL;
import com.ginspiration.webchatui.service.IReportService;
import com.ginspiration.webchatui.util.WxUtil;
import com.ginspiration.webchatui.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/report")
@Slf4j
public class ReportController {

    @Autowired
    private IReportService reportService;
    @Value("${save.file.path}")
    private String saveFilePath;
    @Value("${wx.information.appID}")
    private String appId;
    @Value("${wx.information.appsecret}")
    private String appsecret;

    @GetMapping("/to_report")
    public ModelAndView toReport(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String openId = WxUtil.getOpenId(request, appId, appsecret);
        modelAndView.addObject("openId",openId);
        log.info("用户上报:{}",openId);
        modelAndView.setViewName("to_report");
        return modelAndView;
    }

    @PostMapping("/to_report/get/{openId}")
    @ResponseBody
    public String getReport(@PathParam("file") MultipartFile file, UserReport userReport,@PathVariable("openId")String openId){
        if (file != null) {
            try {
                // 上传的文件名
                String filename = file.getOriginalFilename();
                String suffixName = filename.substring(filename.lastIndexOf("."));
                String fileName = UUID.randomUUID()+suffixName;
                String path = saveFilePath+"/"+fileName;
                file.transferTo(new File(path));
                userReport.setImgUrl(path);
                userReport.setOpenId(openId);
                boolean res = reportService.saveReport(userReport);
                log.info("res with file:{}",res);
                return "ok";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            userReport.setOpenId(openId);
            boolean res = reportService.saveReport(userReport);
            log.info("res without file:{}",res);
        }
        return "ok";
    }
    @GetMapping("/to_report/success")
    public String toSuccess(){
        return "success_page";
    }
    @GetMapping("/to_report/error")
    public String toError(){
        return "error_page";
    }
}