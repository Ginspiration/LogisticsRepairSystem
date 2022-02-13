package com.ginspiration.serverbackground.entity.common;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
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
    private Long number;

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
     * 图片
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf2.format(this.date);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", phone='" + phone + '\'' +
                ", faculty='" + faculty + '\'' +
                ", sort='" + sort + '\'' +
                ", address='" + address + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
