package com.ginspiration.serverbackground.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImgAndArticles {
    private String type;
    private String mediaId;
    private long createdAt;
}
