package com.sungang.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SGang on 2019/1/14.
 */
@Component
@ConfigurationProperties()
@PropertySource(value = "init.properties")
public class InitTagService {
    @Value("${manTag}")
    private String manTag;
    private String womenTag;
    @Value("${tagNum}")
    private int tagNum;
    private int lastIndex;
    public List<String> getInitTag(){
        List<String> list  = Arrays.asList(manTag.split("；"));
        return getFixNumberTag(list);
    }
    private List<String> getFixNumberTag(List<String> list){
        List<String> _list = new ArrayList<>();
        for(int i = 0;i<tagNum;i++){
            int random=(int)(Math.random()*list.size()-1);
            while(lastIndex==random){
                random=(int)(Math.random()*list.size()-1);
            }
            _list.add(list.get(random));
        }
        return _list;
    }

}
