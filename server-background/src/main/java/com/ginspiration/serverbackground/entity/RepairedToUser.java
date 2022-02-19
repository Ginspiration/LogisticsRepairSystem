package com.ginspiration.serverbackground.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class RepairedToUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String openId;
    private String userName;
    private String userPhone;
    private String date;
    private String repairedMessage;
}
