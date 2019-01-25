package com.sungang.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SGang on 2019/1/22.
 */
@Component
@EnableScheduling
public class ScheduledService {
    private Logger logger = LoggerFactory.getLogger(ScheduledService.class);
    @Value("${webchat.appId}")
    private String appId;
    @Value("${webchat.appsecret}")
    private String appsecret;
    @Value("${webchat.access_token_url}")
    private String url;
    @Value("${webchat.access_token_path}")
    private String token_path;
    @Autowired
    private HttpAPIService httpAPIService;

    @Scheduled(fixedRate = 7200*1000)
    public void scheduled1() {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", appId);
        map.put("secret", appsecret);
        map.put("grant_type", "client_credential");
        String result = null;
        try {
            result = httpAPIService.doGet(url, map);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        PrintWriter out = null;
        try {
            File file = new File(token_path);
            out = new PrintWriter(file);
            out.print(result);
            out.flush();
            logger.info("msg: {} write to {}", result, file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            logger.error("can not find file", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

}
