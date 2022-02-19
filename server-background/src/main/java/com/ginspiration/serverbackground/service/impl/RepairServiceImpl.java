package com.ginspiration.serverbackground.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.serverbackground.entity.RepairedToUser;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Maintainer;
import com.ginspiration.serverbackground.entity.common.Repair;
import com.ginspiration.serverbackground.entity.common.Report;
import com.ginspiration.serverbackground.mapper.RepairMapper;
import com.ginspiration.serverbackground.service.IMaintainerService;
import com.ginspiration.serverbackground.service.IRepairService;
import com.ginspiration.serverbackground.service.IReportService;
import com.ginspiration.serverbackground.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@Service
@Slf4j
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Autowired
    private IMaintainerService maintainerService;
    @Autowired
    private RestUtil restUtil;
    @Autowired
    private IReportService reportService;

    @Override
    public RespCommon dealWithReport(int repairId,int reportId, String reportPhone, String repairPhone, int status) {
        LambdaQueryWrapper<Repair> lambdaQuery = Wrappers.lambdaQuery();
        Repair repair = new Repair();
        /*派送*/
        if (status == 1) {
            lambdaQuery.eq(Repair::getReportId, reportId);
            repair.setId(repairId);
            repair.setRepairPhone(repairPhone);
            repair.setStatus(status);
            LambdaQueryWrapper<Maintainer> query = Wrappers.lambdaQuery();
            query.eq(Maintainer::getPhone,repairPhone);
            Maintainer one = maintainerService.getOne(query);
            LambdaUpdateWrapper<Maintainer> lambdaUpdate = Wrappers.lambdaUpdate();
            lambdaUpdate.eq(Maintainer::getPhone,repairPhone).set(Maintainer::getWorkCount,one.getWorkCount()+1);
            maintainerService.update(lambdaUpdate);
            /*完成维修*//*异常*/
        } else if (status == 2 || status == 3) {
            lambdaQuery
                    .eq(Repair::getReportId, reportId);
            repair.setId(repairId);
            repair.setStatus(status);
            if (status == 2) {
                LocalDateTime now = LocalDateTime.now();
                //维修时间入库
                repair.setRepairedDate(now);

                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                RepairedToUser repairedToUser = new RepairedToUser();
                //获取用户数据
                Report report = reportService.lambdaQuery()
                        .eq(Report::getId, reportId)
                        .one();
                //获取维修人信息
                Maintainer maintainer = maintainerService.lambdaQuery()
                        .eq(Maintainer::getPhone,repairPhone)
                        .one();
                repairedToUser.setUserName(report.getName());
                repairedToUser.setUserPhone(report.getPhone());
                repairedToUser.setOpenId(report.getOpenId());
                repairedToUser.setDate(dtf2.format(now));
                String message = "您的上报已维修完成!\\r\\n维修师傅:"+maintainer.getName()+"\\r\\n联系方式:"+maintainer.getPhone();
                repairedToUser.setRepairedMessage(message);
                //RPC调用weUI模块 微信模板
                String res = restUtil.sendRepairedToUser(repairedToUser);

                log.info("send repaired to user:{},status:{}",repairedToUser,res);
            }
        }
        if (this.saveOrUpdate(repair, lambdaQuery)) {
            return new RespCommon(200, "处理成功！");
        } else
            return new RespCommon(500, "系统错误");
    }

    @Override
    public RespCommon getRepairInfoById(int id) {
        LambdaQueryWrapper<Repair> query = Wrappers.lambdaQuery();
        query.eq(Repair::getId, id);
        Repair currRepair = super.getOne(query);
        LambdaQueryWrapper<Maintainer> query1 = Wrappers.lambdaQuery();
        query1.eq(Maintainer::getPhone, currRepair.getRepairPhone());
        Maintainer one = maintainerService.getOne(query1);
        Map<String, Object> res = new HashMap<String, Object>() {{
            put("maintainer", one);
            if (currRepair.getRepairedDate() != null) {
                put("repairedDate",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(currRepair.getRepairedDate()));
            }else
                put("repairedDate","暂无");
        }};
        return new RespCommon(200, res);
    }
}
