package com.ginspiration.serverbackground.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Repair;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
public interface IRepairService extends IService<Repair> {
    RespCommon dealWithReport(int id,String reportPhone,String repairPhone,int status);
    RespCommon getRepairInfoById(int id);
}
