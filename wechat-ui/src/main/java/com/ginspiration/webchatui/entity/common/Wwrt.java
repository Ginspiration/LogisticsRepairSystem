package com.ginspiration.webchatui.entity.common;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author GaoYun
 * @since 2022-01-18
 */
@TableName("wwrt")
public class Wwrt implements Serializable {

    private static final long serialVersionUID = 1L;

      private String wechatWebRefreshToken;

    
    public String getWechatWebRefreshToken() {
        return wechatWebRefreshToken;
    }

      public void setWechatWebRefreshToken(String wechatWebRefreshToken) {
          this.wechatWebRefreshToken = wechatWebRefreshToken;
      }

    @Override
    public String toString() {
        return "Wwrt{" +
              "wechatWebRefreshToken=" + wechatWebRefreshToken +
              "}";
    }
}
