package com.ginspiration.serverbackground.entity.common;

import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("repair")
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 上报人联系手机
     */
    private String reportPhone;

    /**
     * 维修人联系手机
     */
    private String repairPhone;

    /**
     * 维修状态,0未开始,1正在,2已结束,3异常
     */
    private Integer status;

    /**
     * 上报人反馈
     */
    private String feedback;

    /**
     * 维修日期
     */
    private LocalDateTime repairedDate;

    public LocalDateTime getRepairedDate() {
        return repairedDate;
    }

    public void setRepairedDate(LocalDateTime repairedDate) {
        this.repairedDate = repairedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportPhone() {
        return reportPhone;
    }

    public void setReportPhone(String reportPhone) {
        this.reportPhone = reportPhone;
    }

    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", reportPhone=" + reportPhone +
                ", repairPhone=" + repairPhone +
                ", status=" + status +
                ", feedback=" + feedback +
                ",repairedDate=" + repairedDate +
                "}";
    }
}
