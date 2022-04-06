package com.ginspiration.serverbackground.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ginspiration.serverbackground.entity.common.SuperAdmin;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GaoYun
 * @since 2022-04-06
 */
public interface ISuperAdminService extends IService<SuperAdmin> {
    boolean updatePassword(SuperAdmin superAdmin);
    boolean login(SuperAdmin superAdmin);
}
