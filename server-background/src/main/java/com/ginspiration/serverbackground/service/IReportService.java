package com.ginspiration.serverbackground.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Report;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
public interface IReportService extends IService<Report> {

    RespCommon queryAllReportInfo(int curr,int size,int status);

    void exportExcel(HttpServletResponse response, int status);
}
