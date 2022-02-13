package com.ginspiration.serverbackground.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ginspiration.serverbackground.entity.UpLoadTempImg;
import com.ginspiration.serverbackground.entity.common.Announce;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IAnnounceService extends IService<Announce> {
    Map<String, String> uploadPermanentImgFile(MultipartFile file, String access_token);
    UpLoadTempImg uploadTempImgFile(MultipartFile file, String access_token);
}
