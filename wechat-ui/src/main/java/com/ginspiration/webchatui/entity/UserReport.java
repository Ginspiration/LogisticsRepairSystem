package com.ginspiration.webchatui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReport {
    private String name;
    private String openId;
    private String number;
    private String phone;
    private String faculty;//院系
    private String sort;//分类
    private String address;
    private String imgUrl;
    private String description;
    private Date date;
}
