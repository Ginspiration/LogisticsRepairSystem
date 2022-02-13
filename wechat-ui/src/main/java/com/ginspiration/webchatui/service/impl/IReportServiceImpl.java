package com.ginspiration.webchatui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.webchatui.entity.UserReport;
import com.ginspiration.webchatui.entity.common.Repair;
import com.ginspiration.webchatui.entity.common.Report;
import com.ginspiration.webchatui.mapper.ReportMapper;
import com.ginspiration.webchatui.service.IRepairService;
import com.ginspiration.webchatui.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class IReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

    @Autowired
    private IRepairService repairService;
    @Autowired
    private HttpSession session;

    @Override
    public boolean saveReport(UserReport userReport) {
        Report report = new Report();
        report.setOpenId(userReport.getOpenId());
        report.setAddress(userReport.getAddress());
        report.setDescription(userReport.getDescription());
        report.setFaculty(userReport.getFaculty());
        report.setName(userReport.getName());
        report.setPhone(userReport.getPhone());
        report.setNumber(userReport.getNumber());
        report.setSort(userReport.getSort());
        report.setImgUrl(userReport.getImgUrl());
        report.setDate(LocalDateTime.now());

        Repair repair = new Repair();
        repair.setReportPhone(userReport.getPhone());

        return saveOrUpdate(report) && repairService.saveOrUpdate(repair);
    }

}