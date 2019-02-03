package com.sungang.service.impl;

import com.alibaba.fastjson.JSON;
import com.sungang.model.SystemHelper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SGang on 2019/1/30.
 */
@Service
public class QRcodeService {
    private Logger logger = LoggerFactory.getLogger(QRcodeService.class);
    private static String PIC_SUFFIX = ".jpg";

    public String getQRCode(String token, String scene, String page, int width) {
        String[] str = scene.split("&");
        String openid = str[0];
        String type = str[1];
        String path = SystemHelper.getPath() + File.separator + openid + PIC_SUFFIX;
        File file = new File(path);
        if (file.exists()) {
            return "/image/" + openid + PIC_SUFFIX;
        } else {
            return getUserPicCodeNoexits(token, scene, page, width, openid);
        }
    }

    private String getUserPicCodeNoexits(String token, String scene, String page, int width, String openid) {
        token = JSON.parseObject(token).get("access_token").toString();
        logger.info("############"+scene);
        System.out.println(scene.split("&")[0]);
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File savePath = null;
        String filePath = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token;
            logger.info("token is :{}", token);
            Map<String, Object> param = new HashMap<>();
            param.put("scene", scene);
            param.put("page", page);
            param.put("width", width);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            logger.info("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            logger.info("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
            logger.info(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);
            filePath = SystemHelper.getPath() + File.separator + openid + PIC_SUFFIX;
            outputStream = new FileOutputStream(filePath);
            logger.info("文件上传的存储路径为: {}", filePath);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            logger.error("调用小程序生成微信永久小程序码URL接口异常", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "/image/" + openid + PIC_SUFFIX;
    }

}
