package com.ginspiration.serverbackground.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ginspiration.serverbackground.entity.common.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@Mapper
public interface ReportMapper extends BaseMapper<Report> {
    List<Report> queryAllNotDealDeReport(Page<Report> page, @Param("status") int status);
}
