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

import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.modules.entity.Post;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

/**
 * @author langhsu
 *
 */
@CacheConfig(cacheNames = Consts.CACHE_USER)
public interface PostService {



	/**
	 *
	 * @param pageable
	 */
	@Cacheable
	Page<PostVO> paging(Pageable pageable, int channelId, Set<Integer> excludeChannelIds);

	Page<PostVO> paging4Admin(Pageable pageable, int channelId, String title, String telephone, String ngaykham,Integer featured,String diachi);
	/**
	 * @param pageable
	 * @param userId
	 */
	@Cacheable
	Page<PostVO> pagingByAuthorId(Pageable pageable, long userId);

	/**
	 * @param maxResults
	 * @return
	 */
	@Cacheable(key = "'latest_' + #maxResults")
	List<PostVO> findLatestPosts(int maxResults);

	/**
	 * @param maxResults
	 * @return
	 */
	@Cacheable(key = "'hottest_' + #maxResults")
	List<PostVO> findHottestPosts(int maxResults);
	
	/**
	 * @param ids
	 */
	Map<Long, PostVO> findMapByIds(Set<Long> ids);

	/**
	 * @param post
	 */
	@CacheEvict(allEntries = true)
	long post(PostVO post);
	
	/**
	 * @param id
	 * @return
	 */
	@Cacheable(key = "'post_' + #id")
	PostVO get(long id);

	/**
	 */
	@CacheEvict(allEntries = true)
	void update(PostVO p);

	/**
	 * @param id
	 */
	@CacheEvict(allEntries = true)
	void updateFeatured(long id, int featured);

	/**
	 */
	@CacheEvict(allEntries = true)
	void updateWeight(long id, int weighted);
	
	/**
	 * @param id
	 * @param authorId
	 */
	@CacheEvict(allEntries = true)
	void delete(long id, long authorId);

	/**
	 *
	 * @param ids
	 */
	@CacheEvict(allEntries = true)
	void delete(Collection<Long> ids);
	
	/**
	 * @param id
	 */
	@CacheEvict(key = "'view_' + #id")
	void identityViews(long id);
	
	/**
	 * @param id
	 */
	@CacheEvict(key = "'view_' + #id")
	void identityComments(long id);

	/**
	 * @param userId
	 * @param postId
	 */
	@CacheEvict(key = "'view_' + #postId")
	void favor(long userId, long postId);

	/**
	 * @param userId
	 * @param postId
	 */
	@CacheEvict(key = "'view_' + #postId")
	void unfavor(long userId, long postId);

	long count();
}
