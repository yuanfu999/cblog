package com.cyf.cblog.controller.user;

import com.cyf.cblog.config.Constants;
import com.cyf.cblog.controller.common.CommonModelAndView;
import com.cyf.cblog.service.BlogService;
import com.cyf.cblog.service.BlogTagService;
import com.cyf.cblog.service.SysConfigService;
import com.cyf.cblog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenyuanfu
 * @Date 2020/7/22 14:05
 **/
@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTagService tagService;

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 首页
     * @param request
     * @return
     */
    @GetMapping({"/", "index", "index.html"})
    public String index(HttpServletRequest request){
        return this.page(request, 1);
    }

    /**
     * 首页 分页数据
     * @return
     */
    @GetMapping({"/page/{pageNum}"})
    @ResponseBody
    public String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum) {
        PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
        if (blogPageResult == null) {
            return "error/error_404";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("blogPageResult", blogPageResult);
        params.put("pageName", "首页");
        params.put("newBlogs", blogService.getBlogListSortByType(1));
        params.put("hotBlogs", blogService.getBlogListSortByType(0));
        params.put("hotTags", tagService.getHotBlogTag());
        params.put("configurations", sysConfigService.getAllConfigs());
        CommonModelAndView.setCommonModelAndView(request, params);
        return "blog/" + Constants.AMAZE + "/index";
    }

}
