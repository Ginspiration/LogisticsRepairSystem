package com.ginspiration.serverbackground.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Maintainer;
import com.ginspiration.serverbackground.mapper.MaintainerMapper;
import com.ginspiration.serverbackground.service.IMaintainerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@Service
@Slf4j
public class MaintainerServiceImpl extends ServiceImpl<MaintainerMapper, Maintainer> implements IMaintainerService {

    @Autowired
    private  MaintainerMapper maintainerMapper;

    @Override
    public RespCommon getAllMaintainer() {
        LambdaQueryWrapper<Maintainer> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.orderByAsc(Maintainer::getWorkCount);
        List<Maintainer> maintainers = maintainerMapper.selectList(lambdaQuery);
        if (maintainers == null) {
            return new RespCommon(500,"暂无员工信息！");
        }
        maintainers.stream().forEach(v->{
            if (v.getStatus().equals(0)) {
                v.setStatusStr("空闲");
            }else
                v.setStatusStr("正在出工");
        });
        return new RespCommon(200,maintainers);
    }

    @Override
    public void maintainerSaveOrUpdate(Maintainer maintainer) {
        boolean saveOrUpdate = super.saveOrUpdate(maintainer);
        if (StringUtils.isEmpty(maintainer.getId())) {
            log.info("新增维修人：{},状态:{}",maintainer,saveOrUpdate);
        }else {
            log.info("更新维修人：{},状态:{}",maintainer,saveOrUpdate);
        }
    }

    @Override
    public void del(String maintainerId) {
        boolean remove = super.removeById(maintainerId);
        log.info("删除员工-id:{},状态:{}",maintainerId,remove);
    }
}
