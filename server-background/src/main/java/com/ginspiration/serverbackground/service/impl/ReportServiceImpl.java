package com.ginspiration.serverbackground.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Report;
import com.ginspiration.serverbackground.mapper.RepairMapper;
import com.ginspiration.serverbackground.mapper.ReportMapper;
import com.ginspiration.serverbackground.service.IReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
@Slf4j
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public RespCommon queryAllReportInfo(int curr, int size,int status) {
        Page<Report> page = new Page<>(curr,size);
        List<Report> reports = reportMapper.queryAllNotDealDeReport(page,status);
        if (reports == null) {
            return new RespCommon(500, "没有数据!");
        }
        HashMap<String, Object> pageInfo = new HashMap<String, Object>() {{
            put("records", reports);
            put("pages", page.getPages());
            put("total", page.getTotal());
        }};
        log.info("查询到页数:{},总共{}条",page.getPages(),page.getTotal());
        return new RespCommon(200, pageInfo);
    }
}
