package com.ginspiration.serverbackground.vo;

import com.ginspiration.serverbackground.entity.common.Announce;
import lombok.Data;

@Data
public class PublishVo {
    private Integer id;
    private Integer adminId;
    private String adminName;
    private Announce announce;
}
