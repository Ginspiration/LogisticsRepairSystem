package com.ginspiration.serverbackground.controller;


import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Admin;
import com.ginspiration.serverbackground.service.IAdminService;
import com.ginspiration.serverbackground.vo.GetIdVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    @ResponseBody
    public RespCommon login(@RequestBody Admin admin){
        session.invalidate();
        RespCommon respCommon = adminService.loginVerify(admin);
        log.info("respCommon:{}",respCommon);
        return respCommon;
    }
    @PostMapping("/loginOut")
    @ResponseBody
    public RespCommon login(){
        session.invalidate();
        return new RespCommon(200,"退出登录");
    }
    @PostMapping("/getUserInfo")
    @ResponseBody
    public RespCommon getUserInfo(){
        Object adminUser = session.getAttribute("AdminUser");
        log.info("用户在线:{}",adminUser);
        //验证是否是超级管理员
        Object superAdmin = session.getAttribute("SuperAdmin");
        if ( superAdmin != null) {
           return new RespCommon(110,superAdmin);
        }
        return new RespCommon(200,adminUser);
    }
    @PostMapping("/getAllAdmin")
    @ResponseBody
    public RespCommon getAllAdmin(){
        List<Admin> allAdmin = adminService.getAllAdmin();
        return new RespCommon(200,allAdmin);
    }

}