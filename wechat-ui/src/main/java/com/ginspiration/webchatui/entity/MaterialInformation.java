package com.ginspiration.webchatui.entity;

import lombok.Data;

@Data
public class MaterialInformation {
    private String type;//文件类型
    private String mediaId;//media id
    private long createdAt; //创建时间
}
