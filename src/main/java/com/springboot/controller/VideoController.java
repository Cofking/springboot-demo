package com.springboot.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;


/**
 * 视频控制器
 *
 * @author ChenKang
 * @date 2023/3/6 10:05
 */
@Controller
public class VideoController {

    @RequestMapping("showVideoPage")
    public ModelAndView video() {
        ModelAndView mod = new ModelAndView();
        mod.setViewName("video");
        return mod;
    }


    @RequestMapping(value = "showVideo")
    public void showVideo(HttpServletRequest request, HttpServletResponse response){
        String filename= "E:"+ File.separator+"study"+File.separator+"video"+File.separator+"test.mp4";
        try{

            File file = new File(filename);
            long fileLength=file.length();

            // 随机读文件    ！！ 不是流 ！！
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            // 获取从哪个字节开始
            String rangeString = request.getHeader("Range");
            long range=0;
            if (StrUtil.isNotBlank(rangeString)) {
                range = Long.parseLong(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
            }

            //获取响应输出流
            OutputStream os = response.getOutputStream();
//            response.setHeader("Content-Type", "video/mp4");
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
//            response.addHeader("Access-Control-Allow-Origin", "*");
            // 移动访问指针到指定位置
            randomAccessFile.seek(range);

            // 每次请求只返回1MB的视频流
            byte[] bytes = new byte[1024 * 1024];
            //读取文件1MB数据 不足返回真实读取大小
            int len = randomAccessFile.read(bytes);

            //设置此次相应返回的数据长度
            response.setContentLength(len);
            //设置此次相应返回的数据范围
            response.setHeader("Content-Range", "bytes "+range+"-"+((range+len)-1)+"/"+fileLength);
            // 将这1MB的视频流响应给客户端
            os.write(bytes, 0, len);
            os.close();
            randomAccessFile.close();

            System.out.println("返回数据区间:【"+range+"-"+(range+len)+"】");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
