package com.ginspiration.serverbackground.controller;

import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.UpLoadTempImg;
import com.ginspiration.serverbackground.entity.UploadImgAndArticles;
import com.ginspiration.serverbackground.entity.WeChatPublish;
import com.ginspiration.serverbackground.enumeration.RespStatus;
import com.ginspiration.serverbackground.enumeration.WxURL;
import com.ginspiration.serverbackground.service.IAnnounceService;
import com.ginspiration.serverbackground.util.ReadFileUtil;
import com.ginspiration.serverbackground.util.RestUtil;
import com.ginspiration.serverbackground.util.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/announce")
@Slf4j
public class AnnounceController {
    //{"url":"http:\/\/mmbiz.qpic.cn\/mmbiz_png\/GibH5TicBwiaPxE9oy6QIgAulf0GZiao8uDTdJKqLh4kVkpQuwqpondnGZRJLFPQMSesf0lb6SrBeWtiaDsjq8ZR1kA\/0"}
    @Autowired
    private IAnnounceService announceService;
    @Autowired
    private RestUtil restUtil;
    @Autowired
    private HttpSession session;

    @PostMapping("/publish/upload")
    @ResponseBody
    public RespCommon uploadImg(@RequestParam("file") MultipartFile file){
        //Map<String, String> map = announceService.uploadPermanentImgFile(file, restUtil.getWxAccessToken());
        UpLoadTempImg upLoadTempImg = announceService.uploadTempImgFile(file, restUtil.getWxAccessToken());
        log.info("临时图片上传状态:{}",upLoadTempImg);
        session.setAttribute("MEDIA_ID",upLoadTempImg.getMediaId());
        /*存入数据库*/



        return new RespCommon(RespStatus.SUCCESS.getStatus(), "上传成功");
    }
    @PostMapping("/publish/doPublish")
    @ResponseBody
    public RespCommon doPublish(@RequestBody WeChatPublish weChatPublish) {
        String requestUploadUrl = WxURL.UPLOAD_IMG_AND_ARTICLES_URL.getURL().replace("ACCESS_TOKEN", restUtil.getWxAccessToken());//公告上传地址
        String messageUpload = Objects.requireNonNull(ReadFileUtil.readJsonFile("classpath:jsonTemplate/messageUpload.json"))
                .replace("THUMB_MEDIA_ID", (CharSequence) session.getAttribute("MEDIA_ID"))
                .replace("TITLE", weChatPublish.getTitle())
                .replace("CONTENT", weChatPublish.getContent())
                .replace("AUTHOR", weChatPublish.getPublisher())
                ;
        JSONObject jsonObject = HttpUtil.httpsRequest(requestUploadUrl, "POST", messageUpload);
        log.info("图文消息上传状态:{}",jsonObject);
        UploadImgAndArticles uploadImgAndArticles = new UploadImgAndArticles(jsonObject.getString("type"), jsonObject.getString("media_id"), jsonObject.getInt("created_at"));

        //I2bQ83TvzLriRN48qXw6bFPvMqrs6TGqSJa-gOrAv4aoTh6O0hkyn1j-vwA6tzsy
        String requestSendUrl = WxURL.GROUP_SENDING_ACCORDING_LABEL_URL.getURL().replace("ACCESS_TOKEN", restUtil.getWxAccessToken());//公告发布地址
        String messageMass = Objects.requireNonNull(ReadFileUtil.readJsonFile("classpath:jsonTemplate/messageMass.json"))
                .replace("false","true")
                .replace("MEDIA_ID",uploadImgAndArticles.getMediaId())
                ;
        JSONObject jsonObject1 = HttpUtil.httpsRequest(requestSendUrl, "POST", messageMass);
        log.info("图文消息发布状态:{}",jsonObject1);
        return new RespCommon(RespStatus.SUCCESS.getStatus(), "发布成功");
    }
}
