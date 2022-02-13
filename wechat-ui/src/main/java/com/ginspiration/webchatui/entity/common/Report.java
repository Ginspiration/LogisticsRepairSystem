package com.ginspiration.webchatui.entity.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 上报人微信appId
     */
    private String openId;

    /**
     * 上报人姓名
     */
    private String name;

    /**
     * 学号/教工号
     */
    private String number;

    /**
     * 联系手机
     */
    private String phone;

    /**
     * 院系
     */
    private String faculty;

    /**
     * 分类
     */
    private String sort;

    /**
     * 上报的地址
     */
    private String address;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 上报日期
     */
    private LocalDateTime date;

}
