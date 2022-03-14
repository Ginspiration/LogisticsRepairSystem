package com.ginspiration.serverbackground.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.ginspiration.serverbackground.converter.LocalDateConverter;
import com.ginspiration.serverbackground.converter.LocalDateTimeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 风险管控信息表（风险、辨识、评估）
 * </p>
 *
 * @author yhj
 * @since 2022-02-09 09:49:33
 */
@Data
@ContentStyle(horizontalAlignment = HorizontalAlignment.CENTER,
        verticalAlignment = VerticalAlignment.CENTER)
@HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER,
        verticalAlignment = VerticalAlignment.CENTER)
@ColumnWidth(20)
@EqualsAndHashCode(callSuper = false)
public class ExcelVo implements Serializable {
    private static final long serialVersionUID = 1L;


    @ExcelProperty("项目上级单位")
    private String projectParentName;

    @ExcelProperty("项目名称")
    private String projectName;

    @ExcelProperty("分部分项")
    private String subItemName;

    @ExcelProperty("设备设施/人员)")
    private String riskPointDesc;

    @ExcelProperty("潜在风险点/引发事故")
    private String potentialRiskPoints;

    @ExcelProperty("事故类型")
    private String accidentTypeDesc;

    @ExcelProperty("风险等级")
    private String dangerLevelDesc;

    @ExcelProperty("具体部位")
    private String position;

    @ExcelProperty("实施阶段")
    private String executePhase;

    @ExcelProperty(value = "计划实施开始时间",converter = LocalDateConverter.class)
    private LocalDate planStartTime;

    @ExcelProperty(value = "计划实施结束时间",converter = LocalDateConverter.class)
    private LocalDate planEndTime;

    @ExcelProperty("管控层级")
    private String controlLevelDesc;


    @ExcelProperty("责任人")
    private String responsible;

    @ExcelProperty("管控措施")
    private String controlMeasures;

    @ExcelProperty("警示方式")
    private String alarmMethodDesc;

    @ExcelProperty("最近更新人")
    private String lastControlMemberName;

    @ExcelProperty(value = "更新时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime controlPersonTime;
}
