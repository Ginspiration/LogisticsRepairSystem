package com.ginspiration.serverbackground.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ginspiration.serverbackground.entity.UpLoadTempImg;
import com.ginspiration.serverbackground.entity.common.Announce;
import com.ginspiration.serverbackground.enumeration.WxURL;
import com.ginspiration.serverbackground.mapper.AnnounceMapper;
import com.ginspiration.serverbackground.service.IAnnounceService;
import com.ginspiration.serverbackground.vo.PublishVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnnounceServiceImpl extends ServiceImpl<AnnounceMapper, Announce> implements IAnnounceService {

    @Autowired
    private AnnounceMapper announceMapper;


    @Override
    /**
     * 新增永久素材
     * @return
     * @throws Exception
     */
    public Map<String, String> uploadPermanentImgFile(MultipartFile file, String access_token) {
        Map<String, String> resMap = new HashMap<>();
        try {
            String url = WxURL.ADD_PERMANENT_MATERIAL.getURL().replace("ACCESS_TOKEN", access_token);
            URL urlObj = new URL(url);

            // 创建Http连接
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);   // 使用post提交需要设置忽略缓存

            //消息请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");

            //设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);

            StringBuilder sb = new StringBuilder();
            sb.append("--");    // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getOriginalFilename() + "\";filelength=\"" + file.getSize() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");

            byte[] head = sb.toString().getBytes("utf-8");

            // 创建输出流
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 获得输出流表头
            out.write(head);

            //文件正文部分
            DataInputStream in = new DataInputStream(file.getInputStream());
            int bytes = 0;
            byte[] bufferOut = new byte[1024 * 1024 * 10]; // 10M
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();

            //结尾
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
            out.write(foot);
            out.flush();
            out.close();


            // 获取响应
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            String result = null;
            try {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                result = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                reader.close();
            }
            JSONObject jsonObject = JSONObject.fromObject(result);
            resMap.put("url", (String) jsonObject.get("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resMap;
    }
    /**
     * 新增临时素材
     * @return
     * @throws Exception
     */
    @Override
    public UpLoadTempImg uploadTempImgFile(MultipartFile file, String access_token) {

        UpLoadTempImg upLoadTempImg = new UpLoadTempImg();
        try {
            String url = WxURL.ADD_TEMPORARY_MATERIAL.getURL()
                    .replace("ACCESS_TOKEN", access_token)
                    .replace("TYPE","image");
            URL urlObj = new URL(url);

            // 创建Http连接
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);   // 使用post提交需要设置忽略缓存

            //消息请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");

            //设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);

            StringBuilder sb = new StringBuilder();
            sb.append("--");    // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getOriginalFilename() + "\";filelength=\"" + file.getSize() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");

            byte[] head = sb.toString().getBytes("utf-8");

            // 创建输出流
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 获得输出流表头
            out.write(head);

            //文件正文部分
            DataInputStream in = new DataInputStream(file.getInputStream());
            int bytes = 0;
            byte[] bufferOut = new byte[1024 * 1024 * 10]; // 10M
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();

            //结尾
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
            out.write(foot);
            out.flush();
            out.close();


            // 获取响应
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            String result = null;
            try {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                result = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                reader.close();
            }
            JSONObject jsonObject = JSONObject.fromObject(result);
            upLoadTempImg.setType((String)jsonObject.get("type"));
            upLoadTempImg.setMediaId((String)jsonObject.get("media_id"));
            Integer created_at = (Integer) jsonObject.get("created_at");
            upLoadTempImg.setCreatedAt(created_at.longValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return upLoadTempImg;
    }

    @Override
    public List<PublishVo> getPublish(){
        return announceMapper.getPublish();
    }
}
