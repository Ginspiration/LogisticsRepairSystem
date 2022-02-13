package com.ginspiration.serverbackground.entity;

import com.ginspiration.serverbackground.enumeration.RespStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespCommon {
    private int status;
    private Object respBody;
    public RespCommon(int status){
        this.status = status;
    }
}