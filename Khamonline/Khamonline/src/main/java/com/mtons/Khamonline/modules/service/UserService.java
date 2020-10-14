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
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.modules.data.UserVO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

/**
 * @author langhsu
 *
 */
@CacheConfig(cacheNames = Consts.CACHE_USER)
public interface UserService {
	/**
	 */
	Page<UserVO> paging(Pageable pageable, String name);

	Map<Long, UserVO> findMapByIds(Set<Long> ids);

	/**
	 */
	AccountProfile login(String username, String password);

	/**
	 */
	AccountProfile findProfile(Long id);

	/**
	 */
	UserVO register(UserVO user);

	/**
	 * @return
	 */
	@CacheEvict(key = "#user.getId()")
	AccountProfile update(UserVO user);

	/**
	 * @param email
	 * @return
	 */
	@CacheEvict(key = "#id")
	AccountProfile updateEmail(long id, String email);

	/**
	 * @param userId
	 * @return
	 */
	@Cacheable(key = "#userId")
	UserVO get(long userId);

	UserVO getByUsername(String username);

	UserVO getByEmail(String email);

	/**
	 * @param id
	 * @param path
	 * @return
	 */
	@CacheEvict(key = "#id")
	AccountProfile updateAvatar(long id, String path);

	/**
	 * @param id
	 * @param newPassword
	 */
	void updatePassword(long id, String newPassword);

	/**
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 */
	void updatePassword(long id, String oldPassword, String newPassword);

	/**
	 * @param id
	 * @param status
	 */
	void updateStatus(long id, int status);

	long count();

}
