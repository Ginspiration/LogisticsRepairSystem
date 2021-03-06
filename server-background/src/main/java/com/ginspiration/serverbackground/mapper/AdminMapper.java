package com.ginspiration.serverbackground.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ginspiration.serverbackground.entity.common.Admin;
import com.ginspiration.serverbackground.vo.PublishVo;
import org.apache.ibatis.annotations.Mapper;

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
public interface AdminMapper extends BaseMapper<Admin> {

}
