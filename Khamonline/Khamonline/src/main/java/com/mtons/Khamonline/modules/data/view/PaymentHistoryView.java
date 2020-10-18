/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.modules.data.view;

import com.alibaba.fastjson.annotation.JSONField;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.modules.data.UserVO;
import com.mtons.Khamonline.modules.entity.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author langhsu
 * 
 */
public class PaymentHistoryView extends PaymentHistory implements Serializable {
	private static final long serialVersionUID = -1144627551517707139L;

	private User user;
	private Post post;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
