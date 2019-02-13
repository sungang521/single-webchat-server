package com.sungang.controller;

import com.alibaba.fastjson.JSON;
import com.sungang.model.ResultActionBean;
import com.sungang.model.SystemHelper;
import com.sungang.model.User;
import com.sungang.model.result.SaveUserResponse;
import com.sungang.service.ResultActionService;
import com.sungang.service.UserService;
import com.sungang.service.impl.AccessTokenService;
import com.sungang.service.impl.HttpAPIService;
import com.sungang.service.impl.QRcodeService;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private QRcodeService qRcodeService;

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
    public SaveUserResponse saveUser(User user, @RequestParam(value = "shareOpenid", required = false) String shareOpenid, @RequestParam(value = "currentOpenid", required = false) String currentOpenid) {
        if ((!"".equals(shareOpenid) && !"".equals(currentOpenid))) {
            ResultActionBean bean = new ResultActionBean();
            bean.setOpenid(currentOpenid);
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
    public List<String> getminiqrQr(String scene, String page, int width) {
        logger.info("scene {},page {},width {}", scene, page, width);
        return qRcodeService.getQRCode(getAccessToken(), scene, page, width);

    }

}
