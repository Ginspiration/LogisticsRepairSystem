package com.ginspiration.serverbackground.controller;


import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Admin;
import com.ginspiration.serverbackground.entity.common.SuperAdmin;
import com.ginspiration.serverbackground.service.IAdminService;
import com.ginspiration.serverbackground.service.ISuperAdminService;
import com.ginspiration.serverbackground.vo.GetIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GaoYun
 * @since 2022-04-06
 */
@Controller
@RequestMapping("/superAdmin")
public class SuperAdminController {

    @Autowired
    private IAdminService adminService;
    @Resource
    private ISuperAdminService superAdminService;
    @Resource
    private HttpSession session;


    @PostMapping("/login")
    @ResponseBody
    public RespCommon login(@RequestBody SuperAdmin superAdmin){
        session.invalidate();
        boolean status = superAdminService.login(superAdmin);
        if (status) {
            session.setAttribute("SuperAdmin",superAdmin);
            return new RespCommon(200,"登录成功!");
        }else
            return new RespCommon(400,"登录信息错误");
    }

    @GetMapping("/loginOut")
    @ResponseBody
    public RespCommon login(){
        session.invalidate();
        return new RespCommon(200,"已退出登录");
    }


    @PostMapping("/admin/saveOrUpdate")
    @ResponseBody
    public RespCommon saveOrUpdate(@RequestBody Admin admin){
        adminService.adminSaveOrUpdate(admin);
        return new RespCommon(200,"操作成功");
    }
    @PostMapping("/admin/getAllAdmin")
    @ResponseBody
    public RespCommon getAllAdmin(){
        List<Admin> allAdmin = adminService.getAllAdmin();
        return new RespCommon(200,allAdmin);
    }
    @PostMapping("/admin/del")
    @ResponseBody
    public RespCommon del(@RequestBody GetIdVo getIdVo){
        adminService.del(getIdVo.getId());
        return new RespCommon(200,"操作成功");
    }
    @PostMapping("/updatePassword")
    @ResponseBody
    public RespCommon del(@RequestBody SuperAdmin superAdmin){
        boolean status = superAdminService.updatePassword(superAdmin);
        if (status) {
            return new RespCommon(200,"操作成功");
        }else
            return new RespCommon(400,"操作失败");

    }
}

