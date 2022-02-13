package com.ginspiration.webchatui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ginspiration.webchatui.entity.UserReport;
import com.ginspiration.webchatui.entity.common.Report;

public interface IReportService extends IService<Report> {
    boolean saveReport(UserReport report);
}
