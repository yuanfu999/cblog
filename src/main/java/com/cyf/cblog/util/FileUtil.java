package com.cyf.cblog.util;

import com.cyf.cblog.config.Constants;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author chenyuanfu
 * @Date 2020/8/7 10:05
 **/
public class FileUtil {

    /**
     * 文件上传
     * @param request
     * @param response
     * @param file
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String uploadFile(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException, URISyntaxException {
        //获取原文件名
        String fileName = file.getOriginalFilename();
        //获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        //创建文件
        File destFile = new File(Constants.DEV_FILE_UPLOAD_DIC + newFileName);
        File fileDirectory = new File(Constants.DEV_FILE_UPLOAD_DIC);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
            }
        }
        file.transferTo(destFile);
        return newFileName;
    }

}
