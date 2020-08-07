package com.cyf.cblog.controller.admin;

import com.cyf.cblog.util.FileUtil;
import com.cyf.cblog.util.MyBlogUtils;
import com.cyf.cblog.util.Result;
import com.cyf.cblog.util.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author chenyuanfu
 * @Date 2020/8/6 11:22
 **/
@RestController
@RequestMapping("admin")
public class UploadController {

    @PostMapping("upload/file")
    public Result upload(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam("file") MultipartFile file) throws URISyntaxException {
        try {
            String newFileName = FileUtil.uploadFile(request, response, file);
            String fileUrl = MyBlogUtils.getHost(new URI(request.getRequestURL() + "")) + "/upload/" + newFileName;
            Result resultSuccess = ResultGenerator.genSuccessResult();
            //生产环境
            resultSuccess.setData(fileUrl);
            return resultSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败");
        }
    }


}
