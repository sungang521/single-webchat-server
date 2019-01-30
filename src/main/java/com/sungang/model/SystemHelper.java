package com.sungang.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by SGang on 2019/1/30.
 */
public class SystemHelper {
    private static Logger logger = LoggerFactory.getLogger(SystemHelper.class);
    private static String path = null;
    public static String getPath(){
        if(path == null){
            try {
                getPicPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return path;
    }
    private static void getPicPath() throws FileNotFoundException {
        File savePath = new File(getJarRootPath(), "static/code");
        //判断上传文件的保存目录是否存在
        if (!savePath.exists() && !savePath.isDirectory()) {
            logger.info(savePath + "目录不存在，需要创建");
            //创建目录
            boolean created = savePath.mkdirs();
            if (!created) {
                logger.error("路径: '" + savePath.getAbsolutePath() + "'创建失败");
                throw new RuntimeException("路径: '" + savePath.getAbsolutePath() + "'创建失败");
            }
        }
        path = savePath.getAbsolutePath();
    }
    private static String getJarRootPath() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        //=> file:/root/tmp/demo-springboot-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/
        logger.debug("ResourceUtils.getURL(\"classpath:\").getPath() -> "+path);
        //创建File时会自动处理前缀和jar包路径问题  => /root/tmp
        File rootFile = new File(path);
        if(!rootFile.exists()) {
            logger.info("根目录不存在, 重新创建");
            rootFile = new File("");
            logger.info("重新创建的根目录: "+rootFile.getAbsolutePath());
        }
        logger.debug("项目根目录: "+rootFile.getAbsolutePath());
        //获取的字符串末尾没有分隔符 /
        return rootFile.getAbsolutePath();
    }
}
