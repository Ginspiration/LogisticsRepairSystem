package com.ginspiration.serverbackground.entity.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("announce")
public class Announce implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 发布人
     */
      private Integer adminUser;

      /**
     * 公告发布方
     */
      private String publisher;

      /**
     * 公告标题
     */
      private String title;

      /**
     * 图片
     */
      private String imgUrl;

      /**
     * 公告内容
     */
      private String content;

      /**
     * 发布时间
     */
      private LocalDateTime date;

      @TableField(exist = false)
      private String dateStr;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public Integer getAdminUser() {
        return adminUser;
    }

      public void setAdminUser(Integer adminUser) {
          this.adminUser = adminUser;
      }
    
    public String getPublisher() {
        return publisher;
    }

      public void setPublisher(String publisher) {
          this.publisher = publisher;
      }
    
    public String getTitle() {
        return title;
    }

      public void setTitle(String title) {
          this.title = title;
      }
    
    public String getImgUrl() {
        return imgUrl;
    }

      public void setImgUrl(String imgUrl) {
          this.imgUrl = imgUrl;
      }
    
    public String getContent() {
        return content;
    }

      public void setContent(String content) {
          this.content = content;
      }
    
    public LocalDateTime getDate() {
        return date;
    }

      public void setDate(LocalDateTime date) {
          this.date = date;
      }

    @Override
    public String toString() {
        return "Announce{" +
              "id=" + id +
                  ", adminUser=" + adminUser +
                  ", publisher=" + publisher +
                  ", title=" + title +
                  ", imgUrl=" + imgUrl +
                  ", content=" + content +
                  ", date=" + date +
              "}";
    }
}
