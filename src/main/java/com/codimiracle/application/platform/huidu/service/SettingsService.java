package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.Setting;
import com.codimiracle.web.mybatis.contract.Service;


public interface SettingsService extends Service<String, Setting> {
    void save(String name, String value);

    String retrieve(String name);

}
