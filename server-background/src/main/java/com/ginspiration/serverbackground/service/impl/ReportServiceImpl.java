package com.ginspiration.serverbackground.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Maintainer;
import com.ginspiration.serverbackground.entity.common.Report;
import com.ginspiration.serverbackground.mapper.RepairMapper;
import com.ginspiration.serverbackground.mapper.ReportMapper;
import com.ginspiration.serverbackground.service.IMaintainerService;
import com.ginspiration.serverbackground.service.IReportService;
import com.ginspiration.serverbackground.util.ExportUtil;
import com.ginspiration.serverbackground.vo.ExcelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private IMaintainerService maintainerService;

    @Override
    public RespCommon queryAllReportInfo(int curr, int size, int status) {
        Page<Report> page = new Page<>(curr, size);
        List<Report> reports = reportMapper.queryAllNotDealDeReport(page, status);
        if (reports == null) {
            return new RespCommon(500, "没有数据!");
        }
        HashMap<String, Object> pageInfo = new HashMap<String, Object>() {{
            put("records", reports);
            put("pages", page.getPages());
            put("total", page.getTotal());
        }};
        log.info("查询到页数:{},总共{}条", page.getPages(), page.getTotal());
        return new RespCommon(200, pageInfo);
    }

    @Override
    public void exportExcel(HttpServletResponse response, int status) {
        Page<Report> page = new Page<>(0, -1);
        List<Report> reports = reportMapper.queryAllNotDealDeReport(page, status);
        log.info("report message:{}", reports);
        List<ExcelVo> excelVos = CglibUtil.copyList(reports, ExcelVo::new);

        //维修人信息
        Map<String, Maintainer> maintainerMap = maintainerService
                .lambdaQuery()
                .list()
                .stream()
                .filter(v -> StrUtil.isNotEmpty(v.getPhone()))
                .collect(Collectors.toMap(Maintainer::getPhone, e -> e, (k1, k2) -> k1));

        Map<String, ExcelVo> excelVoMap = excelVos
                .stream()
                .filter(v -> StrUtil.isNotEmpty(v.getRepairPhone()))
                .collect(Collectors.toMap(ExcelVo::getRepairPhone, e -> e, (k1, k2) -> k1));

        for (Report report : reports) {
            Maintainer maintainer = maintainerMap.get(report.getRepairPhone());
            ExcelVo excelVo = excelVoMap.get(report.getRepairPhone());
            if (maintainer != null && excelVo != null) {
                excelVo.setRepairName(maintainer.getName());
            }
        }

        ExportUtil.download(response, "表格", excelVos, ExcelVo.class);
    }

}
