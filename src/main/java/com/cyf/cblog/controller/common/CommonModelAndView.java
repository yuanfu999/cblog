package com.cyf.cblog.controller.common;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author chenyuanfu
 * @Date 2020/8/3 20:57
 **/
public class CommonModelAndView {

    public static void setCommonModelAndView(HttpServletRequest request, Map<String, Object> params){
        request.setAttribute("blogPageResult", params.get("blogPageResult"));
        request.setAttribute("pageName", params.get("pageName"));
        request.setAttribute("pageUrl", params.get("pageName"));
        request.setAttribute("keyword", params.get("keyword"));
        request.setAttribute("newBlogs", params.get("newBlogs"));
        request.setAttribute("hotBlogs", params.get("hotBlogs"));
        request.setAttribute("hotTags", params.get("hotTags"));
        request.setAttribute("configurations", params.get("configurations"));
    }

}
