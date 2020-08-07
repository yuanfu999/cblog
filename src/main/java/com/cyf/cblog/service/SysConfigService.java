package com.cyf.cblog.service;

import java.util.Map;

public interface SysConfigService {

    Map<String, String> getAllConfigs();

    int updateConfig(String configName, String configValue);
}
