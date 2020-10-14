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

import com.mtons.Khamonline.base.lang.Consts;
import org.springframework.cache.annotation.CacheEvict;

import java.util.Set;

/**
 * @author langhsu on 2015/8/6.
 */
public interface UserEventService {
    /**
     * @param userId
     */
    @CacheEvict(value = {Consts.CACHE_USER, Consts.CACHE_POST}, allEntries = true)
    void identityPost(Long userId, boolean plus);

    /**
     * @param userId
     */
    @CacheEvict(value = {Consts.CACHE_USER, Consts.CACHE_POST}, allEntries = true)
    void identityComment(Long userId, boolean plus);

    /**
     */
    @CacheEvict(value = {Consts.CACHE_USER, Consts.CACHE_POST}, allEntries = true)
    void identityComment(Set<Long> userIds, boolean plus);
}
