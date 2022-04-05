package com.ginspiration.serverbackground.controller;

import com.ginspiration.serverbackground.entity.RespCommon;
import com.ginspiration.serverbackground.entity.UpLoadTempImg;
import com.ginspiration.serverbackground.entity.UploadImgAndArticles;
import com.ginspiration.serverbackground.entity.WeChatPublish;
import com.ginspiration.serverbackground.entity.common.Admin;
import com.ginspiration.serverbackground.entity.common.Announce;
import com.ginspiration.serverbackground.enumeration.RespStatus;
import com.ginspiration.serverbackground.enumeration.WxURL;
import com.ginspiration.serverbackground.service.IAnnounceService;
import com.ginspiration.serverbackground.util.ReadFileUtil;
import com.ginspiration.serverbackground.util.RestUtil;
import com.ginspiration.serverbackground.util.http.HttpUtil;
import com.ginspiration.serverbackground.vo.PublishVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
    @Value("${save.file.announceFile}")
    private String announceFile;
    @Autowired
    private HttpSession session;

    @PostMapping("/publish/upload")
    @ResponseBody
    public RespCommon uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        if(file == null){
            return new RespCommon(RespStatus.ERROR400.getStatus(), "上传失败，请重试");
        }
        //Map<String, String> map = announceService.uploadPermanentImgFile(file, restUtil.getWxAccessToken());
        UpLoadTempImg upLoadTempImg = announceService.uploadTempImgFile(file, restUtil.getWxAccessToken());
        log.info("临时图片上传状态:{}",upLoadTempImg);
        session.setAttribute("MEDIA_ID",upLoadTempImg.getMediaId());

        /*存入磁盘*/
        File fileDir = new File(announceFile);
        if(!fileDir.exists()) {
            //如果没有目录应该创建目录
            fileDir.mkdirs();
        }
        //获取图片名称
        String mediaId = upLoadTempImg.getMediaId();
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = mediaId + suffixName;
        String path = announceFile+"/"+fileName;
        //保存
        file.transferTo(new File(path));

        //session中临时保存
        session.setAttribute("ImgUrl","/announce/"+fileName);

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

        //存入数据库
        Announce announce = new Announce();
        announce.setImgUrl((String) session.getAttribute("ImgUrl"));
        announce.setTitle(weChatPublish.getTitle());
        announce.setContent(weChatPublish.getContent());
        announce.setPublisher(weChatPublish.getPublisher());
        announce.setDate(LocalDateTime.now());
        Admin adminUser = (Admin) session.getAttribute("AdminUser");
        announce.setAdminUser(adminUser.getId());
        boolean save = announceService.save(announce);
        log.info("公告是否保存成功:{}",save);
        //删除多余信息
        session.removeAttribute("ImgUrl");

        return new RespCommon(RespStatus.SUCCESS.getStatus(), "发布成功");
    }

    /**
     * 获取公告信息
     * @param publishVo
     * @return
     */
    @PostMapping("/publish/getPublish")
    @ResponseBody
    public RespCommon getPublish(@RequestBody PublishVo publishVo){
        List<PublishVo> publish = announceService.getPublish();
        return new RespCommon(RespStatus.SUCCESS.getStatus(), publish);
    }
}
