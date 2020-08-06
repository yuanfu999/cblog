package com.cyf.cblog.controller.user;

import com.cyf.cblog.config.Constants;
import com.cyf.cblog.controller.common.CommonModelAndView;
import com.cyf.cblog.entity.BlogComment;
import com.cyf.cblog.entity.FriendLink;
import com.cyf.cblog.service.*;
import com.cyf.cblog.util.*;
import com.cyf.cblog.vo.BlogDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenyuanfu
 * @Date 2020/7/22 14:00
 * 博客控制类
 **/
@Controller
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCommentService blogCommentService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 获取博客详情
     * @param blogId
     * @return
     */
    @GetMapping("/{blogId}")
    public String getBlogDetailByBlogId(HttpServletRequest request,
                                              @PathVariable("blogId") Long blogId,
                                              @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage){
        BlogDetailVO blogDetail = blogService.getBlogDetailVOByBlogId(blogId);
        if(blogDetail != null){
            PageResult blogComments = blogCommentService.getBlogCommentListByPage(blogId, commentPage);
            request.setAttribute("blogDetailVO", blogDetail);
            request.setAttribute("commentPageResult", blogComments);
        }
        request.setAttribute("pageName", "详情");
        request.setAttribute("configurations", sysConfigService.getAllConfigs());
        return "blog/" + Constants.AMAZE + "/detail";
    }

    /**
     * 标签列表页
     *
     * @return
     */
    @GetMapping({"/tag/{tagName}/{page}"})
    public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsListByTag(tagName, page);
        Map<String, Object> params = new HashMap<>();
        params.put("blogPageResult", blogPageResult);
        params.put("pageName", "标签");
        params.put("pageUrl", "tag");
        params.put("keyword", tagName);
        params.put("newBlogs", blogService.getBlogListSortByType(1));
        params.put("hotBlogs", blogService.getBlogListSortByType(0));
        params.put("hotTags", blogTagService.getHotBlogTag());
        params.put("configurations", sysConfigService.getAllConfigs());
        CommonModelAndView.setCommonModelAndView(request, params);
        return "blog/" + Constants.AMAZE + "/list";
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
        return search(request, keyword, 1);
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @GetMapping({"/search/{keyword}/{page}"})
    public String search(HttpServletRequest request, @PathVariable("keyword") String keyword, @PathVariable("page") Integer page) {
        PageResult blogPageResult = blogService.getBlogsPageBySearch(keyword, page);
        Map<String, Object> params = new HashMap<>();
        params.put("blogPageResult", blogPageResult);
        params.put("pageName", "搜索");
        params.put("pageUrl", "search");
        params.put("keyword", keyword);
        params.put("newBlogs", blogService.getBlogListSortByType(1));
        params.put("hotBlogs", blogService.getBlogListSortByType(0));
        params.put("hotTags", blogTagService.getHotBlogTag());
        params.put("configurations", sysConfigService.getAllConfigs());
        CommonModelAndView.setCommonModelAndView(request, params);
        return "blog/" + Constants.AMAZE + "/list";
    }

    /**
     * 友情链接页
     *
     * @return
     */
    @GetMapping({"/link"})
    public String link(HttpServletRequest request) {
        request.setAttribute("pageName", "友情链接");
        Map<Byte, List<FriendLink>> linkMap = friendLinkService.getLinkListByPage();
        if (linkMap != null) {
            //判断友链类别并封装数据 0-友链 1-推荐 2-个人网站
            if (linkMap.containsKey((byte) 0)) {
                request.setAttribute("favoriteLinks", linkMap.get((byte) 0));
            }
            if (linkMap.containsKey((byte) 1)) {
                request.setAttribute("recommendLinks", linkMap.get((byte) 1));
            }
            if (linkMap.containsKey((byte) 2)) {
                request.setAttribute("personalLinks", linkMap.get((byte) 2));
            }
        }
        request.setAttribute("configurations", sysConfigService.getAllConfigs());
        return "blog/" + Constants.AMAZE + "/link";
    }

    /**
     * 评论操作
     */
    @PostMapping(value = "/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request, HttpSession session,
                          @RequestParam Long blogId, @RequestParam String verifyCode,
                          @RequestParam String commentator, @RequestParam String email,
                          @RequestParam String websiteUrl, @RequestParam String commentBody) {
        if (StringUtils.isEmpty(verifyCode)) {
            return ResultGenerator.genFailResult("验证码不能为空");
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (!verifyCode.equals(kaptchaCode)) {
            return ResultGenerator.genFailResult("验证码错误");
        }
        String ref = request.getHeader("Referer");
        if (StringUtils.isEmpty(ref)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (null == blogId || blogId < 0) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (StringUtils.isEmpty(commentator)) {
            return ResultGenerator.genFailResult("请输入称呼");
        }
        if (StringUtils.isEmpty(email)) {
            return ResultGenerator.genFailResult("请输入邮箱地址");
        }
        if (!PatternUtil.isEmail(email)) {
            return ResultGenerator.genFailResult("请输入正确的邮箱地址");
        }
        if (StringUtils.isEmpty(commentBody)) {
            return ResultGenerator.genFailResult("请输入评论内容");
        }
        if (commentBody.trim().length() > 200) {
            return ResultGenerator.genFailResult("评论内容过长");
        }
        BlogComment comment = new BlogComment();
        comment.setBlogId(blogId);
        comment.setCommentator(MyBlogUtils.cleanString(commentator));
        comment.setEmail(email);
        if (PatternUtil.isURL(websiteUrl)) {
            comment.setWebsiteUrl(websiteUrl);
        }
        comment.setCommentBody(MyBlogUtils.cleanString(commentBody));
        return ResultGenerator.genSuccessResult(blogCommentService.addComment(comment));
    }

    /**
     * 查找subUrl的博客,比如关于我们
     *
     * @return
     */
    @GetMapping({"/url/{subUrl}"})
    public String detail(HttpServletRequest request, @PathVariable("subUrl") String subUrl) {
        BlogDetailVO blogDetailVO = blogService.getBlogDetailBySubUrl(subUrl);
        if (blogDetailVO != null) {
            request.setAttribute("blogDetailVO", blogDetailVO);
            request.setAttribute("pageName", subUrl);
            request.setAttribute("configurations", sysConfigService.getAllConfigs());
            return "blog/" + Constants.AMAZE + "/detail";
        } else {
            return "error/error_400";
        }
    }

}
