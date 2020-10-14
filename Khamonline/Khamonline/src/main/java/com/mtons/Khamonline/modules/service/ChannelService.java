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

import com.mtons.Khamonline.modules.entity.Channel;
import com.mtons.Khamonline.modules.entity.Post;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author langhsu
 *
 */
public interface ChannelService {
	List<Channel> findAll(int status);
	Map<Integer, Channel> findMapByIds(Collection<Integer> ids);
	Channel getById(int id);
	void update(Channel channel);
	void updateWeight(int id, int weighted);
	void delete(int id);
	long count();
}
