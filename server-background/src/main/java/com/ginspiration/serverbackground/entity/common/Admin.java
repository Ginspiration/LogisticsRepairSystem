package com.ginspiration.serverbackground.entity.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("admin")
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 管理员名
     */
      private String name;

      /**
     * 管理员密码
     */
      private String password;

      /**
     * 联系手机
     */
      private String phone;

    
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
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getPhone() {
        return phone;
    }

      public void setPhone(String phone) {
          this.phone = phone;
      }

    @Override
    public String toString() {
        return "Admin{" +
              "id=" + id +
                  ", name=" + name +
                  ", password=" + password +
                  ", phone=" + phone +
              "}";
    }
}
