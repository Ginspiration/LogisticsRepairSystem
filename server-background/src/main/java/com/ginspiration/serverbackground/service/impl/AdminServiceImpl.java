package com.ginspiration.serverbackground.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.common.Admin;
import com.ginspiration.serverbackground.mapper.AdminMapper;
import com.ginspiration.serverbackground.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private HttpSession session;

    @Override
    public RespCommon loginVerify(Admin admin) {
        Admin adminInfo = this.query().eq("phone", admin.getPhone()).one();
        if (ObjectUtil.isNotNull(adminInfo)) {
            if (!adminInfo.getPassword().equals(admin.getPassword())) {
                return new RespCommon(500, "密码错误!");
            } else {
                log.info("用户登录:{}", adminInfo);
                session.setAttribute("AdminUser", adminInfo);
                return new RespCommon(200);
            }
        } else
            return new RespCommon(500,"用户不存在！");
    }

    @Override
    public void adminSaveOrUpdate(Admin admin) {
        boolean saveOrUpdate = super.saveOrUpdate(admin);
        if (StringUtils.isEmpty(admin.getId())) {
            log.info("新增管理员：{},状态:{}",admin,saveOrUpdate);
        }else {
            Admin adminTemp = this.query().eq("phone", admin.getPhone()).one();
            session.setAttribute("AdminUser",adminTemp);
            log.info("更新管理员：{},状态:{}",admin,saveOrUpdate);
        }
    }

    @Override
    public void del(String id) {
        boolean remove = super.removeById(id);
        log.info("删除管理员-id:{},状态:{}",id,remove);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return super.lambdaQuery().list();
    }
}
