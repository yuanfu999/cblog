package com.cyf.cblog.config;

/**
 * @Author chenyuanfu
 * @Date 2020/7/28 21:08
 **/
public class  Constants {

    //主题风格
    public static String AMAZE = "amaze";
    public static String DEFAULT = "default";
    public static String YUMMY_JEKYLL = "yummy-jekyll";

    //文件存放路径
    //生产环境
    public final static String FILE_UPLOAD_DIC = "/opt/deploy/upload/";//上传文件的默认url前缀，根据部署设置自行修改
    //开发环境
    public final static String DEV_FILE_UPLOAD_DIC = "E:\\upload";//上传文件的默认url前缀，根据部署设置自行修改

}
