package com.ginspiration.webchatui.controller;

import com.alibaba.fastjson.JSONObject;
import com.ginspiration.webchatui.service.IRepairService;
import com.ginspiration.webchatui.util.WxUtil;
import com.ginspiration.webchatui.vo.RecordVO;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/repair")
public class RepairController {
    @Value("${wx.information.appID}")
    private String appId;
    @Value("${wx.information.appsecret}")
    private String appsecret;
    @Autowired
    IRepairService repairService;

    @GetMapping("/record")
    public ModelAndView history(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();//oFkTJ50r4ZP5D-7Rakt9XYSEIti8
        String saveOpenId = request.getParameter("openId");
        String openId;
        if (saveOpenId != null) {
            openId = saveOpenId;
        } else
            openId = WxUtil.getOpenId(request, appId, appsecret);
        List<RecordVO> recordVOS = repairService.repairRecord(openId);
        //List<RecordVO> recordVOS = repairService.repairRecord("oFkTJ50r4ZP5D-7Rakt9XYSEIti8");
        //将list转换为json格式
        String records = JSONArray.fromObject(recordVOS).toString();
        modelAndView.addObject("records", records);
        modelAndView.addObject("openId", openId);
        modelAndView.setViewName("record");
        return modelAndView;
    }
}
