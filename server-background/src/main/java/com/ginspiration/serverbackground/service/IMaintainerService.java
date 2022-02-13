package com.ginspiration.serverbackground.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Maintainer;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
public interface IMaintainerService extends IService<Maintainer> {

    RespCommon getAllMaintainer();

    void maintainerSaveOrUpdate(Maintainer maintainer);

    void del(String maintainerId);
}

