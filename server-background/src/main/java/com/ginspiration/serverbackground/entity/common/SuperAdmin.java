package com.ginspiration.serverbackground.entity.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author GaoYun
 * @since 2022-04-06
 */
@TableName("super_admin")
public class SuperAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 账号
     */
      private String account;

      /**
     * 密码
     */
      private String password;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getAccount() {
        return account;
    }

      public void setAccount(String account) {
          this.account = account;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }

    @Override
    public String toString() {
        return "SuperAdmin{" +
              "id=" + id +
                  ", account=" + account +
                  ", password=" + password +
              "}";
    }
}
