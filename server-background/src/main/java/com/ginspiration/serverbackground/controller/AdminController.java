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
        RespCommon respCommon = adminService.loginVerify(admin);
        log.info("respCommon:{}",respCommon);
        return respCommon;
    }
    @PostMapping("/getUserInfo")
    @ResponseBody
    public RespCommon getUserInfo(){
        log.info("用户在线:{}",session.getAttribute("AdminUser"));
        return new RespCommon(200,session.getAttribute("AdminUser"));
    }

    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public RespCommon saveOrUpdate(@RequestBody Admin admin){
        adminService.adminSaveOrUpdate(admin);
        return new RespCommon(200,"操作成功");
    }
    @PostMapping("/getAllAdmin")
    @ResponseBody
    public RespCommon getAllAdmin(){
        List<Admin> allAdmin = adminService.getAllAdmin();
        return new RespCommon(200,allAdmin);
    }
    @PostMapping("/del")
    @ResponseBody
    public RespCommon del(@RequestBody GetIdVo getIdVo){
        adminService.del(getIdVo.getId());
        return new RespCommon(200,"操作成功");
    }
}