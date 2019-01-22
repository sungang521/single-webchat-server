package com.sungang.service.impl;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by SGang on 2019/1/22.
 */
@Service
public class AccessTokenService {
    public String getToken(){
        BufferedReader bufferedReader = null;
        String msg = null;
        try {
            File file = org.springframework.util.ResourceUtils.getFile("classpath:access_token.txt");
             bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            msg = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ee){
            ee.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return msg;
    }
}
