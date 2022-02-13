package com.ginspiration.serverbackground.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Admin;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
public interface IAdminService extends IService<Admin> {
    RespCommon loginVerify(Admin admin);
    void adminSaveOrUpdate(Admin admin);
    void del(String id);
    List<Admin> getAllAdmin();
}
