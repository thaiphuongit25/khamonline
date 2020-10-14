/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.modules.service;

/**
 * @author langhsu on 2015/8/14.
 */
public interface SecurityCodeService {
    /**
     */
    String generateCode(String key, int type, String target);

    /**
     * @return token
     */
    boolean verify(String key, int type, String code);
}
