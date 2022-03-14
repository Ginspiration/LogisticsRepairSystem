package com.ginspiration.serverbackground.util;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@Component
@Slf4j
public class ExportUtil {
    /**
     * excel文件导出
     *
     * @param response HttpServletResponse
     * @param fileName 导出文件名称
     * @param list     导出数据列表
     * @param t        数据列表对象
     * @param <T>
     */
    public static <T> void download(HttpServletResponse response, String fileName, List<T> list, Class<T> t) {
        try {
            if (log.isInfoEnabled()) {
                log.info("[download] list:{}", JSONUtil.toJsonStr(list));
            }
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            if (log.isInfoEnabled()) {
                log.info("[download] EasyExcel write start");
            }

            EasyExcel.write(response.getOutputStream(), t).autoCloseStream(Boolean.FALSE).sheet(fileName).doWrite(list);
        } catch (Exception e) {
            log.error("[download] EasyExcel write error:{}", e);
        }
    }

}
