package com.sungang.controller;

import com.alibaba.fastjson.JSON;
import com.sungang.model.ResultActionBean;
import com.sungang.model.User;
import com.sungang.model.result.SaveUserResponse;
import com.sungang.service.ResultActionService;
import com.sungang.service.UserService;
import com.sungang.service.impl.AccessTokenService;
import com.sungang.service.impl.HttpAPIService;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SGang on 2019/1/21.
 */
@RestController
public class AuthenticationController extends BaseController {
    @Value("${webchat.appId}")
    private String appId;
    @Value("${webchat.appsecret}")
    private String appsecret;
    @Value("${webchat.openIdUrl}")
    private String openIdUrl;
    @Autowired
    private HttpAPIService httpAPIService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private ResultActionService resultActionService;

    @RequestMapping(value = "/getUserMsg", method = RequestMethod.GET)
    public String getOpenId(String code) {
        logger.info("request webchat server url: {},appid={},secret={},js_code={}", openIdUrl, appId, appsecret, code);
        Map<String, Object> map = new HashMap<>();
        map.put("appid", appId);
        map.put("secret", appsecret);
        map.put("js_code", code);
        map.put("grant_type",
                "authorization_code");
        String result = null;
        try {
            result = httpAPIService.doGet(openIdUrl, map);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/uploadUserMsg", method = RequestMethod.GET)
    public SaveUserResponse saveUser(User user, @RequestParam(value = "shareOpenid", required = false) String shareOpenid, @RequestParam(value = "currentOpenid", required = false) String openid) {
        if (shareOpenid != null || openid != null) {
            ResultActionBean bean = new ResultActionBean();
            bean.setOpenid(openid);
            bean.setShareOpenid(shareOpenid);
            bean.setCreateTime(new Timestamp(new Date().getTime()));
            resultActionService.saveResultAction(bean);
        }
        logger.info("the user that request server is [{}]", user);
        userService.saveUser(user);
        return SaveUserResponse.success();
    }

    @RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
    public String getAccessToken() {
        return accessTokenService.getToken();
    }

    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    public Map getminiqrQr(String sceneStr) {
        String token = getAccessToken();
        token = JSON.parseObject(token).get("access_token").toString();
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token;
            logger.info("token is :{}", token);
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            //param.put("page", "pages/index/index");
            param.put("width", 430);
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
            File file = new File("E:/1.png");
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
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
        return null;
    }
}
