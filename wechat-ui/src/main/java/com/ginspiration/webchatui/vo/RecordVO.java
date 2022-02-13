package com.ginspiration.webchatui.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordVO {
    private String repairId;//oFkTJ50r4ZP5D-7Rakt9XYSEIti8
    private String reportPhone;
    private String repairPhone;
    private String status;
    private String feedback;
    private LocalDateTime repairedDate;

    private String reportId;
    private String openId;
    private String name;/*必填*/
    private String number;
    private String phone;/*必填*/
    private String faculty;
    private String sort;
    private String address;/*必填*/
    private String imgUrl;
    private String description;/*必填*/
    private LocalDateTime date;
}
