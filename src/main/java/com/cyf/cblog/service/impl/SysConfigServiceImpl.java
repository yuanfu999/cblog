package com.cyf.cblog.service.impl;

import com.cyf.cblog.entity.SysConfig;
import com.cyf.cblog.mapper.SysConfigMapper;
import com.cyf.cblog.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysConfigServiceImpl  implements SysConfigService{

    public static final String websiteName = "CBlog";
    public static final String websiteDescription = "CBlog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.";
    public static final String websiteLogo = "/admin/dist/img/logo2.png";
    public static final String websiteIcon = "/admin/dist/img/favicon.png";

    public static final String yourAvatar = "/admin/dist/img/13.png";
    public static final String yourEmail = "3125659849@qq.com";
    public static final String yourName = "yuanfu";

    public static final String footerAbout = "CBlog. have fun.";
    public static final String footerICP = "浙ICP备 xxxxxx-x号";
    public static final String footerCopyRight = "@2020 yuanfu";
    public static final String footerPoweredBy = "CBlog";
    public static final String footerPoweredByURL = "##";

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public Map<String, String> getAllConfigs() {
        List<SysConfig> sysConfigs = sysConfigMapper.selectAll();
        Map<String, String> sysConfigMap = sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigName, SysConfig::getConfigValue));
        for(Map.Entry<String, String> config : sysConfigMap.entrySet()){
            if ("websiteName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteName);
            }
            if ("websiteDescription".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteDescription);
            }
            if ("websiteLogo".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteLogo);
            }
            if ("websiteIcon".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(websiteIcon);
            }
            if ("yourAvatar".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourAvatar);
            }
            if ("yourEmail".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourEmail);
            }
            if ("yourName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(yourName);
            }
            if ("footerAbout".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerAbout);
            }
            if ("footerICP".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerICP);
            }
            if ("footerCopyRight".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerCopyRight);
            }
            if ("footerPoweredBy".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerPoweredBy);
            }
            if ("footerPoweredByURL".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())) {
                config.setValue(footerPoweredByURL);
            }
        }
        return sysConfigMap;
    }

    @Override
    public int updateConfig(String configName, String configValue) {
        SysConfig blogConfig = sysConfigMapper.selectByPrimaryKey(configName);
        if (blogConfig != null) {
            blogConfig.setConfigValue(configValue);
            blogConfig.setUpdateTime(new Date());
            return sysConfigMapper.updateByPrimaryKeySelective(blogConfig);
        }
        return 0;
    }
}
