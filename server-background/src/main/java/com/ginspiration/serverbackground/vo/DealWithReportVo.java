package com.ginspiration.serverbackground.vo;

import lombok.Data;

@Data
public class DealWithReportVo {
    private String reportPhone;
    private String repairPhone;
    private Integer status;
    private Integer repairId;
    private Integer reportId;
}
