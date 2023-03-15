package com.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ChenKang
 * @date 2023/3/7 15:24
 */
@ConfigurationProperties("chenkang")
@Component
public class ChenKangConfig {

    private String profile;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
