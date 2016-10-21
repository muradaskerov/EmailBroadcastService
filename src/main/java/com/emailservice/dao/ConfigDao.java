package com.emailservice.dao;

import com.emailservice.models.Config;

import java.util.List;

/**
 * Created by murad on 10/19/16.
 */
public interface ConfigDao {
    public List<Config> getConfig();
    public void updateConfig(Config config);
}
