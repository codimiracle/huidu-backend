package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.Service;
import com.codimiracle.application.platform.huidu.entity.po.Setting;


public interface SettingsService extends Service<String, Setting> {
    void save(String name, String value);

    String retrive(String name);

}
