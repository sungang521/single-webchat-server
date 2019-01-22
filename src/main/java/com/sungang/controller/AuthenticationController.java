package com.sungang.controller;

import com.sungang.model.User;
import com.sungang.model.result.SaveUserResponse;
import com.sungang.service.UserService;
import com.sungang.service.impl.AccessTokenService;
import com.sungang.service.impl.HttpAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public SaveUserResponse saveUser(User user) {
        logger.info("the user that request server is [{}]",user);
        userService.saveUser(user);
        return SaveUserResponse.success();
    }
    @RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
    public String getAccessToken() {
        return accessTokenService.getToken();
    }

}
