package com.ginspiration.webchatui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ginspiration.webchatui.entity.common.Repair;
import com.ginspiration.webchatui.vo.RecordVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
public interface IRepairService extends IService<Repair> {
    List<RecordVO> repairRecord(String openId);
}
