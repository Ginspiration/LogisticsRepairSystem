package com.ginspiration.webchatui.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.webchatui.entity.common.Repair;
import com.ginspiration.webchatui.mapper.RepairMapper;
import com.ginspiration.webchatui.service.IRepairService;
import com.ginspiration.webchatui.vo.RecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private RepairMapper repairMapper;

    @Override
    public List<RecordVO> repairRecord(String openId) {
        if (StrUtil.isNotEmpty(openId)) {
            return repairMapper.repairRecord(openId);
        }
        return null;
    }
}
