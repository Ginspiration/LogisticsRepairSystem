package com.ginspiration.webchatui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ginspiration.webchatui.entity.common.Repair;
import com.ginspiration.webchatui.vo.RecordVO;
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
public interface RepairMapper extends BaseMapper<Repair> {
    List<RecordVO> repairRecord(@Param("openId") String openId);
}
