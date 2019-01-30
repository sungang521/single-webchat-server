package com.sungang;

import com.sungang.interceptor.PicCodeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by SGang on 2019/1/30.
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
       // registry.addInterceptor(new PicCodeInterceptor()).addPathPatterns("/**");
    }

}
