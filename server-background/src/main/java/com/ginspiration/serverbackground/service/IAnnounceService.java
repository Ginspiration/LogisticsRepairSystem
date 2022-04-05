package com.ginspiration.serverbackground.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ginspiration.serverbackground.entity.UpLoadTempImg;
import com.ginspiration.serverbackground.entity.common.Announce;
import com.ginspiration.serverbackground.vo.PublishVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IAnnounceService extends IService<Announce> {
    Map<String, String> uploadPermanentImgFile(MultipartFile file, String access_token);
    UpLoadTempImg uploadTempImgFile(MultipartFile file, String access_token);
    List<PublishVo> getPublish();
}
