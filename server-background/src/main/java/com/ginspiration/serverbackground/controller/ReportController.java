package com.ginspiration.serverbackground.controller;


import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.service.IReportService;
import com.ginspiration.serverbackground.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;


    @PostMapping("/getReportInfo")
    @ResponseBody
    public RespCommon getReportInfo(@RequestBody PageVo pageVo) {
        return reportService.queryAllReportInfo(pageVo.getCurr(),pageVo.getSize(),pageVo.getStatus());
    }
}

