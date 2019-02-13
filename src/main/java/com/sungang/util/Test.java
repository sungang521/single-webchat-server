package com.sungang.util;

import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by SGang on 2019/2/13.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\ideaworkSpace\\singler-server\\src\\main\\resources\\pic\\6.png");
        File tfile = new File("D:\\ideaworkSpace\\singler-server\\src\\main\\resources\\pic\\1.png");
        File erweifile = new File("D:\\ideaworkSpace\\singler-server\\src\\main\\resources\\pic\\3.png");
        File codefile = new File("D:\\ideaworkSpace\\singler-server\\src\\main\\resources\\pic\\code.png");
        File headfile = new File("D:\\ideaworkSpace\\singler-server\\src\\main\\resources\\pic\\132.jpg");
        FileInputStream fis = new FileInputStream(file);
        Image img = ImageIO.read(fis);
        //3、创建画布，根据背景图片的宽高
        BufferedImage bufferedImage = new BufferedImage(
                //宽度
                img.getWidth(null),
                //高度
                img.getHeight(null),
                //图片类型
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        g.drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        g.drawImage(ImageIO.read(tfile), 11, 97, 347, 106, null);
        g.drawImage(ImageIO.read(erweifile), 142, 568, 91, 91, null);
        g.drawImage(ImageIO.read(codefile), 169, 587, 40, 40, null);
        g.setColor(Color.WHITE);
        g.fillRoundRect(76, 0, 233, 31, 0, 16);
        Font rewardFirstFont = new Font("PingFang SC Bold", Font.PLAIN, 13);
//        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(rewardFirstFont);
//        metrics.getAscent()
        Graphics2DUtils.drawString(g, Color.decode("#F35C5C"), rewardFirstFont, "AI智能测字，运势、感情啥都能测！", 90, 20);
        Graphics2DUtils.drawString(g, Color.decode("#303437"), rewardFirstFont, "长按扫码测字", 155, 630);
//        g.gdrawImage(ImageIO.read(file), 100, 700, 175, 175, null);
        g.setColor(Color.YELLOW);
        g.fillRoundRect(97, 96, 104, 25, 13, 13);
        g.fillRoundRect(202, 93, 116, 25, 13, 13);
        g.fillOval(152,82,72,72);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", new File("e://1.jpg"));


    }
}
