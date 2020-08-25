package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.*;
import com.cyf.cblog.mapper.*;
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

    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public PageResult getBlogsForIndexPage(Integer pageNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", pageNum);
        params.put("limit", 10);
        params.put("blogStatus", 1);
        //params.put("userId", userId);
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
        if (!CollectionUtils.isEmpty(blogTags)) {
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

    private List<BlogListVO> getBlogListVOs(List<Blog> blogList) {
        List<BlogListVO> blogListVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(blogList)) {
            List<Integer> categoryIds = blogList.stream().map(Blog::getBlogCategoryId).collect(Collectors.toList());
            List<BlogCategory> categoryList = blogCategoryMapper.findCategoryListByIds(categoryIds);
            Map<Integer, String> categoryMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(categoryList)) {
                categoryMap = categoryList.stream().collect(Collectors.toMap(BlogCategory::getCategoryId, BlogCategory::getCategoryIcon, (key1, key2) -> key2));
            }
            for (Blog blog : blogList) {
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

    @Override
    public PageResult getBlogsPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit());
        List<Blog> blogList = blogMapper.findBlogList(pageUtil);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return new PageResult(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    public Blog getBlogById(Long blogId) {
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        return blog;
    }

    @Override
    public String saveBlog(Blog blog) {
        if (blog.getBlogId() == -1 || blog.getBlogId() == 0) {
            BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
            if (blogCategory == null) {
                blog.setBlogCategoryId(0);
                blog.setBlogCategoryName("默认分类");
            } else {
                blog.setBlogCategoryName(blogCategory.getCategoryName());
                blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
            }
            String[] tagNames = blog.getBlogTags().split(",");
            if (tagNames.length > 6) {
                return "标签数量过多";
            }
            if (blogMapper.insertSelective(blog) == 1) {
                blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
                List<BlogTag> newTags = new ArrayList<>();
                List<BlogTag> allTags = new ArrayList<>();
                for (String tagName : tagNames) {
                    Example example = new Example(BlogTag.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("isDeleted", 0).andEqualTo("tagName", tagName);
                    BlogTag blogTag = blogTagMapper.selectOneByExample(example);
                    if (blogTag == null) {
                        BlogTag tempTag = new BlogTag();
                        tempTag.setTagName(tagName);
                        tempTag.setUseNum(1L);
                        tempTag.setCreateTime(new Date());
                        newTags.add(tempTag);
                    } else {
                        blogTag.setUseNum(blogTag.getUseNum() + 1);
                        blogTagMapper.updateByPrimaryKeySelective(blogTag);
                        allTags.add(blogTag);
                    }
                }
                if (!CollectionUtils.isEmpty(newTags)) {
                    blogTagMapper.insertBlogTagList(newTags);
                }
                allTags.addAll(newTags);
                List<BlogTagRelation> blogTagRelations = new ArrayList<>();
                for (BlogTag tag : allTags) {
                    BlogTagRelation blogTagRelation = new BlogTagRelation();
                    blogTagRelation.setBlogId(blog.getBlogId());
                    blogTagRelation.setTagId(tag.getTagId());
                    blogTagRelation.setCreateTime(new Date());
                    blogTagRelations.add(blogTagRelation);
                }
                Boolean boo = blogTagRelationMapper.insertBlogTagRelationList(blogTagRelations) > 0;
                if (boo) {
                    return "success";
                } else {
                    return "fail";
                }
            }
            return "fail";
        } else {
            Blog blogForUpdate = blogMapper.selectByPrimaryKey(blog.getBlogId());
            if (blogForUpdate == null) {
                return "博客不存在";
            }
            blogForUpdate.setBlogTitle(blog.getBlogTitle());
            blogForUpdate.setBlogSubUrl(blog.getBlogSubUrl());
            blogForUpdate.setBlogContent(blog.getBlogContent());
            blogForUpdate.setBlogCoverImage(blog.getBlogCoverImage());
            blogForUpdate.setBlogStatus(blog.getBlogStatus());
            blogForUpdate.setEnableComment(blog.getEnableComment());
            BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
            if (blogCategory == null) {
                blogForUpdate.setBlogCategoryId(0);
                blogForUpdate.setBlogCategoryName("默认分类");
            } else {
                blogForUpdate.setBlogCategoryId(blog.getBlogCategoryId());
                blogForUpdate.setBlogCategoryName(blogCategory.getCategoryName());
                blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
            }
            String[] tagNamesForUpdate = blog.getBlogTags().split(",");
            if (tagNamesForUpdate.length > 6) {
                return "标签数量过多";
            }
            if (blogMapper.updateByPrimaryKeySelective(blogForUpdate) == 1) {
                blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
                List<BlogTag> newTagsForUpdate = new ArrayList<>();
                List<BlogTag> allTagsForUpdate = new ArrayList<>();
                for (String tagName : tagNamesForUpdate) {
                    Example example = new Example(BlogTag.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("isDeleted", 0).andEqualTo("tagName", tagName);
                    BlogTag blogTag = blogTagMapper.selectOneByExample(example);
                    if (blogTag == null) {
                        BlogTag tempTag = new BlogTag();
                        tempTag.setTagName(tagName);
                        tempTag.setUseNum(1L);
                        tempTag.setCreateTime(new Date());
                        newTagsForUpdate.add(tempTag);
                    } else {
                        blogTagMapper.updateByPrimaryKeySelective(blogTag);
                        allTagsForUpdate.add(blogTag);
                    }
                }
                if (!CollectionUtils.isEmpty(newTagsForUpdate)) {
                    blogTagMapper.insertBlogTagList(newTagsForUpdate);
                }
                allTagsForUpdate.addAll(newTagsForUpdate);
                blogTagRelationMapper.deleteBlogTagRelationList(allTagsForUpdate);
                List<BlogTagRelation> blogTagRelations = new ArrayList<>();
                for (BlogTag tag : allTagsForUpdate) {
                    Example example = new Example(BlogTagRelation.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("tagId", tag.getTagId()).andEqualTo("blogId", blog.getBlogId());
                    BlogTagRelation blogTag = blogTagRelationMapper.selectOneByExample(example);
                    if (blogTag != null) {
                        continue;
                    }
                    BlogTagRelation blogTagRelation = new BlogTagRelation();
                    blogTagRelation.setBlogId(blog.getBlogId());
                    blogTagRelation.setTagId(tag.getTagId());
                    blogTagRelation.setCreateTime(new Date());
                    blogTagRelations.add(blogTagRelation);
                }
                if (!CollectionUtils.isEmpty(blogTagRelations)) {
                    Integer updateTagCount = blogTagRelationMapper.insertBlogTagRelationList(blogTagRelations);
                    if (updateTagCount <= 0) {
                        return "fail";
                    }
                }
                return "success";
            }
            return "fail";
        }
    }


    @Override
    public boolean deleteBlogIds(Integer[] ids) {
        return blogMapper.deleteBlogByIdsForLogic(ids) > 0;
    }
}

