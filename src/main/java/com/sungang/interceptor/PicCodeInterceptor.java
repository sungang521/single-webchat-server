package com.sungang.interceptor;

import com.sungang.model.SystemHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by SGang on 2019/1/30.
 */
public class PicCodeInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(PicCodeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String uri = request.getRequestURI();
        if(uri.startsWith("/image")){
            String name  = uri.substring(uri.lastIndexOf("/")+1);
            String path = SystemHelper.getPath()+File.separator+name;
            deletePic(path);
            logger.info("{} has remove from disk",path);
        }

    }
    private void deletePic(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }
}
