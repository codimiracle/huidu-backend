package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Setting;
import com.codimiracle.application.platform.huidu.mapper.SettingsMapper;
import com.codimiracle.application.platform.huidu.service.SettingsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class SettingsServiceImpl extends AbstractService<String, Setting> implements SettingsService {
    @Resource
    private SettingsMapper settingsMapper;

    @Override
    public void save(String name, String value) {
        Setting setting = findBy("name", name);
        if (Objects.isNull(setting)) {
            setting = new Setting();
            setting.setName(name);
            setting.setValue(value);
            save(setting);
        } else {
            setting.setValue(value);
            update(setting);
        }
    }

    @Override
    public String retrive(String name) {
        Setting setting = findBy("name", name);
        if (Objects.nonNull(setting)) {
            return setting.getValue();
        }
        return null;
    }
}
