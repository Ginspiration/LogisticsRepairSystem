package com.ginspiration.serverbackground.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ginspiration.serverbackground.entity.common.SuperAdmin;
import com.ginspiration.serverbackground.mapper.SuperAdminMapper;
import com.ginspiration.serverbackground.service.ISuperAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GaoYun
 * @since 2022-04-06
 */
@Service
public class SuperAdminServiceImpl extends ServiceImpl<SuperAdminMapper, SuperAdmin> implements ISuperAdminService {

    @Override
    public boolean updatePassword(SuperAdmin superAdmin) {
        return super.updateById(superAdmin);
    }

    @Override
    public boolean login(SuperAdmin superAdmin) {
        SuperAdmin one = super.lambdaQuery()
                .eq(SuperAdmin::getAccount, superAdmin.getAccount())
                .eq(SuperAdmin::getPassword, superAdmin.getPassword())
                .one();
        return one != null;
    }
}
