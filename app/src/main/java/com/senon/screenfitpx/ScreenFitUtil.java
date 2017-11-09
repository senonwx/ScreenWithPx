package com.senon.screenfitpx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 作者：senon on 2017/11/1 09:50
 * 邮箱：a1083911695@163.com
 *
 * 本适配是适配所有以px为单位的dimens生成工具类
 * 需要修改的参数为：
 * 1：xml文件输出地址rootPath前面的String
 * 2：模版长宽参数（UI给的模版）
 * 3：需要适配的分辨率大小，已配置好主流的分辨率
 */

public class ScreenFitUtil {
    /**
     * 配置xml输出地址
     */
    private final static String rootPath = "C:\\Users\\senon\\Desktop"  +  "/value_root/values-{0}x{1}";

    /**
     * 配置模版长宽参数（px）
     */
    private final static float dw = 750f;
    private final static float dh = 1334f;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    public static void main(String[] args) {
        /**
         * 配置需要适配的分辨率
         */
        makeString(320, 480);
        makeString(480, 800);
        makeString(480, 854);
        makeString(540, 960);
        makeString(600, 1024);
        makeString(720, 1184);
        makeString(720, 1196);
        makeString(720, 1280);
        makeString(750, 1334);
        makeString(768, 1024);
        makeString(800, 1280);
        makeString(1080, 1812);
        makeString(1080, 1920);
        makeString(1440, 2560);
    }

    public static void makeString(int w, int h) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw = w / dw;
        for (int i = 1; i < dw; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}", change(cellw * i) + ""));
        }
        sb.append(WTemplate.replace("{0}",(int)dw+"").replace("{1}", w + ""));
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh = h / dh;
        for (int i = 1; i < dh; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}", change(cellh * i) + ""));
        }
        sb2.append(HTemplate.replace("{0}",(int)dh+"").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path, "lay_x.xml");
        File layyFile = new File(path, "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}
