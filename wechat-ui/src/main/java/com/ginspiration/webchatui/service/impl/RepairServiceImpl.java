package com.ginspiration.webchatui.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.webchatui.access.WxAccessToken;
import com.ginspiration.webchatui.entity.RepairedToUser;
import com.ginspiration.webchatui.entity.common.Repair;
import com.ginspiration.webchatui.enumeration.WxURL;
import com.ginspiration.webchatui.mapper.RepairMapper;
import com.ginspiration.webchatui.service.IRepairService;
import com.ginspiration.webchatui.util.ReadFileUtil;
import com.ginspiration.webchatui.util.http.HttpUtil;
import com.ginspiration.webchatui.vo.RecordVO;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements IRepairService {

    @Autowired
    private RepairMapper repairMapper;

    @Autowired
    private WxAccessToken wxAccessToken;

    @Value("${wx.information.templateId}")
    private String templateId;


    @Override
    public List<RecordVO> repairRecord(String openId) {
        if (StrUtil.isNotEmpty(openId)) {
            return repairMapper.repairRecord(openId);
        }
        return null;
    }

    @Override
    public String sendRepairedToUser(RepairedToUser repairedToUser) {
        String s = ReadFileUtil.readJsonFile("classpath:jsonTemplate/SendRepairedToUser.json");
        String template = Objects.requireNonNull(ReadFileUtil.readJsonFile("classpath:jsonTemplate/SendRepairedToUser.json"))
                .replace("OPENID",repairedToUser.getOpenId())
                .replace("TEMPLATE_ID",templateId)
                .replace("FIRST",repairedToUser.getUserName())
                .replace("KEYWORD1",repairedToUser.getUserPhone())
                .replace("KEYWORD2",repairedToUser.getDate())
                .replace("KEYWORD3",repairedToUser.getRepairedMessage())
                .replace("REMARK","感谢你为学校的美好做出的贡献!")
                ;
        log.info("模板消息：{}",template);
        String requestUrl = WxURL.SEND_REPAIRED_TO_USER.getURL().replace("ACCESS_TOKEN",wxAccessToken.getAccessToken());
        JSONObject jsonObject = HttpUtil.httpsRequest(requestUrl, "POST", template);
        log.info("模板消息状态：{}",jsonObject);
        if (!"ok".equals(jsonObject.get("errmsg"))) {
            return "发送指指定用户失败";
        }
        return "成功发送至指定用户";
    }
}
