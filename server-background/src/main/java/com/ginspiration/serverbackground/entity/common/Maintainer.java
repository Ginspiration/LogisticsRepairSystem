package com.ginspiration.serverbackground.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-13
 */
@TableName("maintainer")
@Data
public class Maintainer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 维修人姓名
     */
    private String name;

    /**
     * 维修人手机号
     */
    private String phone;

    /**
     * 维修状态,1正在,0空闲
     */
    private Integer status;

    @TableField(exist = false)
    private String statusStr;

    /**
     * 出工次数
     */
    private Integer workCount;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWorkCount() {
        return workCount;
    }

    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }

    @Override
    public String toString() {
        return "Maintainer{" +
                "id=" + id +
                ", name=" + name +
                ", phone=" + phone +
                ", status=" + status +
                ", workCount=" + workCount +
                "}";
    }
}
