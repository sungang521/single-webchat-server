package com.sungang;

import com.sungang.model.SystemHelper;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 项目配置类
 */
@Configuration
@EnableAutoConfiguration
public class Config  {
    private Logger logger = LoggerFactory.getLogger(Config.class);
    ///////////////httpclient config start/////////////////
    @Value("${http.maxTotal}")
    private Integer maxTotal;
    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;
    @Value("${http.connectTimeout}")
    private Integer connectTimeout;
    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;
    @Value("${http.socketTimeout}")
    private Integer socketTimeout;
    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;
    ///////////httpclient config end///////////////////////
    //////////////////////https config start//////////////

    @Value("${https.port}")
    private Integer port;

    @Value("${https.ssl.key-store-password}")
    private String key_store_password;

    @Value("${https.ssl.key-password}")
    private String key_password;

    //////////////////////https config end//////////////
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加http
        return tomcat;
    }


    // 配置https
    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            //  File keystore = new ClassPathResource("keystore.keystore").getFile();
            ClassPathResource resource = new ClassPathResource("www.xkhome.online.pfx");
            String tempPath = "www.xkhome.online.pfx";
            File f = new File(tempPath);

            IOUtils.copy(resource.getInputStream(), new FileOutputStream(f));
            logger.info("the temp file : [{}] store ", tempPath);
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(port);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(f.getAbsolutePath());
            protocol.setKeystorePass(key_store_password);
           // protocol.setKeyPass(key_password);
            return connector;
        } catch (IOException ex) {
            throw new IllegalStateException("can't access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", ex);
        }
    }

    /**
     * 首先实例化一个连接池管理器，设置最大连接数、并发连接数     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        // 并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器。
     * 这里需要以参数形式注入上面实例化的连接池管理器
     *
     * @param httpClientConnectionManager
     * @return
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
        //HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager);
        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient     * @param httpClientBuilder     * @return
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     *
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
    }

    /**
     * 使用builder构建一个RequestConfig对象
     * @param builder
     * @return
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //和页面有关的静态目录都放在项目的static目录下
//     //   registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
//        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
//        registry.addResourceHandler("/picCode/**").addResourceLocations("file:"+ SystemHelper.getPath());
//        super.addResourceHandlers(registry);
//    }
}
