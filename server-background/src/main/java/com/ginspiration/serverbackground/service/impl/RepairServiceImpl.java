package com.ginspiration.serverbackground.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Maintainer;
import com.ginspiration.serverbackground.entity.common.Repair;
import com.ginspiration.serverbackground.mapper.RepairMapper;
import com.ginspiration.serverbackground.service.IMaintainerService;
import com.ginspiration.serverbackground.service.IRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Autowired
    private IMaintainerService maintainerService;

    @Override
    public RespCommon dealWithReport(int id, String reportPhone, String repairPhone, int status) {
        LambdaQueryWrapper<Repair> lambdaQuery = Wrappers.lambdaQuery();
        Repair repair = new Repair();
        /*派送*/
        if (status == 1) {
            lambdaQuery.eq(Repair::getReportPhone, reportPhone);
            repair.setId(id);
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
                    .eq(Repair::getRepairPhone, repairPhone)
                    .eq(Repair::getReportPhone, reportPhone);
            repair.setId(id);
            repair.setStatus(status);
            if (status == 2) {
                repair.setRepairedDate(LocalDateTime.now());
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
