package com.ginspiration.serverbackground.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatPublish {
    private String publisher;
    private String title;
    private String content;
}
