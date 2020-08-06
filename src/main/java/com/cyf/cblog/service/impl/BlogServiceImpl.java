package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.Blog;
import com.cyf.cblog.entity.BlogCategory;
import com.cyf.cblog.entity.BlogComment;
import com.cyf.cblog.entity.BlogTag;
import com.cyf.cblog.mapper.BlogCategoryMapper;
import com.cyf.cblog.mapper.BlogCommentMapper;
import com.cyf.cblog.mapper.BlogMapper;
import com.cyf.cblog.mapper.BlogTagMapper;
import com.cyf.cblog.service.BlogService;
import com.cyf.cblog.util.MarkDownUtil;
import com.cyf.cblog.util.PageQueryUtil;
import com.cyf.cblog.util.PageResult;
import com.cyf.cblog.vo.BlogDetailVO;
import com.cyf.cblog.vo.BlogListVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public PageResult getBlogsForIndexPage(Integer pageNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", pageNum);
        params.put("limit", 10);
        params.put("blogStatus", 1);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageHelper.startPage(pageQueryUtil.getPage(), pageQueryUtil.getLimit());
        List<Blog> blogList = blogMapper.findBlogList(pageQueryUtil);
        List<BlogListVO> blogListVOs = getBlogListVOs(blogList);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return new PageResult(blogListVOs, pageInfo.getTotal(), pageInfo.getSize(), pageInfo.getPageNum());
    }

    @Override
    public List<Blog> getBlogListSortByType(Integer type) {
        List<Blog> blogList = blogMapper.findBlogListSortByType(type, 9);
        return blogList;
    }

    @Override
    public BlogDetailVO getBlogDetailVOByBlogId(Long blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        List<BlogTag> blogTags = blogTagMapper.findBlogTagByBlogId(blogId);
        if(!CollectionUtils.isEmpty(blogTags)){
            String tagNamesStr = blogTags.stream().map(BlogTag::getTagName).collect(Collectors.joining(","));
            blog.setBlogTags(tagNamesStr);
        }
        BlogDetailVO blogDetailVO = getBlogDetailVO(blog);
        return blogDetailVO;
    }

    @Override
    public PageResult getBlogsListByTag(String tagName, Integer pageNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", pageNum);
        params.put("limit", 10);
        params.put("tagName", tagName);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageHelper.startPage(pageNum, (Integer) params.get("limit"));
        List<Blog> blogList = blogMapper.findBlogsListByTag(pageQueryUtil);
        List<BlogListVO> blogListVOs = getBlogListVOs(blogList);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return new PageResult(blogListVOs, pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    public PageResult getBlogsPageBySearch(String keyword, Integer pageNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", pageNum);
        params.put("limit", 1);
        params.put("keyword", keyword);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageHelper.startPage(pageQueryUtil.getPage(), pageQueryUtil.getLimit());
        List<Blog> blogList = blogMapper.findBlogList(pageQueryUtil);
        List<BlogListVO> blogListVOs = getBlogListVOs(blogList);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return new PageResult(blogListVOs, pageInfo.getTotal(), pageInfo.getSize(), pageInfo.getPageNum());
    }

    @Override
    public BlogDetailVO getBlogDetailBySubUrl(String subUrl) {
        Example example = new Example(Blog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogSubUrl", subUrl).andEqualTo("isDeleted", 0);
        List<Blog> blogs = blogMapper.selectByExample(example);
        return getBlogDetailVO(blogs.get(0));
    }

    private BlogDetailVO getBlogDetailVO(Blog blog) {
        if (blog != null && blog.getBlogStatus() == 1) {
            //增加浏览量
            blog.setBlogViews(blog.getBlogViews() + 1);
            blogMapper.updateByPrimaryKey(blog);
            BlogDetailVO blogDetailVO = new BlogDetailVO();
            BeanUtils.copyProperties(blog, blogDetailVO);
            blogDetailVO.setBlogContent(MarkDownUtil.mdToHtml(blogDetailVO.getBlogContent()));
            BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
            if (blogCategory == null) {
                blogCategory = new BlogCategory();
                blogCategory.setCategoryId(0);
                blogCategory.setCategoryName("默认分类");
                blogCategory.setCategoryIcon("/admin/dist/img/category/00.png");
            }
            //分类信息
            blogDetailVO.setBlogCategoryIcon(blogCategory.getCategoryIcon());
            if (!StringUtils.isEmpty(blog.getBlogTags())) {
                //标签设置
                List<String> tags = Arrays.asList(blog.getBlogTags().split(","));
                blogDetailVO.setBlogTags(tags);
            }
            //设置评论数
            Example example = new Example(BlogComment.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("blogId", blog.getBlogId()).andEqualTo("isDeleted", 0).andEqualTo("commentStatus", 1);
            Integer commentCount = blogCommentMapper.selectCountByExample(example);
            blogDetailVO.setCommentCount(commentCount);
            return blogDetailVO;
        }
        return null;
    }

    private List<BlogListVO> getBlogListVOs(List<Blog> blogList){
        List<BlogListVO> blogListVOS = new ArrayList<>();
        if(!CollectionUtils.isEmpty(blogList)){
            List<Integer> categoryIds = blogList.stream().map(Blog::getBlogCategoryId).collect(Collectors.toList());
            List<BlogCategory> categoryList = blogCategoryMapper.findCategoryListByIds(categoryIds);
            Map<Integer, String> categoryMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(categoryList)){
                categoryMap = categoryList.stream().collect(Collectors.toMap(BlogCategory::getCategoryId, BlogCategory::getCategoryIcon, (key1, key2) -> key2));
            }
            for(Blog blog : blogList){
                BlogListVO blogListVO = new BlogListVO();
                BeanUtils.copyProperties(blog, blogListVO);
                blogListVO.setBlogCategoryIcon(categoryMap.get(blog.getBlogCategoryId()));
                blogListVOS.add(blogListVO);
            }
        }
        return blogListVOS;
    }

    @Override
    public Integer getBlogsCount() {
        Example example = new Example(Blog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0).andEqualTo("blogStatus", 1);
        return blogMapper.selectCountByExample(example);
    }
}

