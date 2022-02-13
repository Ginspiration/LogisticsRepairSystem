package com.ginspiration.serverbackground.controller;


import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.service.IRepairService;
import com.ginspiration.serverbackground.vo.DealWithReportVo;
import com.ginspiration.serverbackground.vo.GetInfoByIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private IRepairService repairService;

    @PostMapping("/dealWithReport")
    public RespCommon dealWithReport(@RequestBody DealWithReportVo deal){
        return repairService.dealWithReport(deal.getId(),deal.getReportPhone(), deal.getRepairPhone(), deal.getStatus());
    }
    @PostMapping("/getRepairInfoById")
    public RespCommon getRepairInfoById(@RequestBody GetInfoByIdVo getInfoById){
        return repairService.getRepairInfoById(getInfoById.getId());
    }
}

