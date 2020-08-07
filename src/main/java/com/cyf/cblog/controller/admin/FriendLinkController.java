package com.cyf.cblog.controller.admin;

import com.cyf.cblog.entity.FriendLink;
import com.cyf.cblog.service.FriendLinkService;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.Result;
import com.cyf.cblog.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Author chenyuanfu
 * @Date 2020/8/6 11:20
 **/
@Controller
@RequestMapping("admin")
public class FriendLinkController {

    @Resource
    private FriendLinkService friendLinkService;

    @GetMapping("/links")
    public String linkPage(HttpServletRequest request) {
        request.setAttribute("path", "links");
        return "admin/link";
    }

    @GetMapping("/links/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(friendLinkService.getBlogLinkPage(pageUtil));
    }

    /**
     * 友链添加
     *
     * @param linkId          链接id
     * @param linkType        链接类型
     * @param linkName        链接名称
     * @param linkUrl         链接url
     * @param linkRank        链接排名分数
     * @param linkDescription 链接描述
     * @return
     */
    @RequestMapping(value = "/links/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam(value = "linkId", defaultValue = "-1") Integer linkId,
                       @RequestParam("linkType") Integer linkType,
                       @RequestParam("linkName") String linkName,
                       @RequestParam("linkUrl") String linkUrl,
                       @RequestParam("linkRank") Integer linkRank,
                       @RequestParam("linkDescription") String linkDescription) {
        if (linkType == null || linkType < 0 || linkRank == null || linkRank < 0 || StringUtils.isEmpty(linkName) || StringUtils.isEmpty(linkName) || StringUtils.isEmpty(linkUrl) || StringUtils.isEmpty(linkDescription)) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        FriendLink link = new FriendLink();
        link.setLinkId(linkId);
        link.setLinkType(linkType.byteValue());
        link.setLinkRank(linkRank);
        link.setLinkName(linkName);
        link.setLinkUrl(linkUrl);
        link.setLinkDescription(linkDescription);
        link.setCreateTime(new Date());
        return ResultGenerator.genSuccessResult(friendLinkService.saveLink(link));
    }

    /**
     * 详情
     */
    @GetMapping("/links/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id) {
        FriendLink link = friendLinkService.selectById(id);
        return ResultGenerator.genSuccessResult(link);
    }

    /**
     * 友链删除
     */
    @RequestMapping(value = "/links/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (friendLinkService.deleteFriendLinkByIds(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
