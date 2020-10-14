package com.mtons.Khamonline.base.oauth.utils;

import com.mtons.Khamonline.base.oauth.APIConfig;


public class OathConfig {
    public static String getValue(String key) {
        return APIConfig.getInstance().getValue(key);
    }
}
