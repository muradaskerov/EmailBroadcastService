package com.emailservice.services.Impl;

import com.emailservice.dao.ConfigDao;
import com.emailservice.models.Config;
import com.emailservice.services.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by murad on 10/19/16.
 */
@Service("configService")
@Transactional
public class ConfigServiceImpl implements ConfigService {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private ConfigDao configDao;

    public List<Config> getConfig() {
        return this.configDao.getConfig();
    }

    public void updateConfig(Config config) {
        this.configDao.updateConfig(config);
    }

}
