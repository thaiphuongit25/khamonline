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

import com.mtons.Khamonline.modules.entity.Options;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;


/**
 * @author langhsu
 *
 */
public interface OptionsService {
	/**
	 * @return list
	 */
	List<Options> findAll();

	/**
	 * @param options
	 */
	void update(Map<String, String> options);

	void initSettings(Resource resource);
}
