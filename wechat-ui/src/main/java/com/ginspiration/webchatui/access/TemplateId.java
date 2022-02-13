package com.ginspiration.webchatui.access;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class TemplateId {
    @Value("${wx.information.templateId}")
    private String templateId;

    public boolean isGetTemplateId(){
        return !(templateId==null);
    }
}
