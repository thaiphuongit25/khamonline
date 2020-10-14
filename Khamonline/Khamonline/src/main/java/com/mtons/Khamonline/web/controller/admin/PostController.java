/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.web.controller.admin;

import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.entity.Post;
import com.mtons.Khamonline.modules.service.ChannelService;
import com.mtons.Khamonline.modules.service.PostService;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.base.lang.Result;
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.modules.service.impl.PostServiceImpl;
import com.mtons.Khamonline.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author langhsu
 *
 */
@Controller("adminPostController")
@RequestMapping("/admin/post")
public class PostController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private ChannelService channelService;

	@Autowired
	private PostServiceImpl postService1;
	
	@RequestMapping("/list")
	public String list(String title,String telephone,String ngaykham,String diachi, ModelMap model, HttpServletRequest request) {
		long id = ServletRequestUtils.getLongParameter(request, "id", Consts.ZERO);
		int channelId = ServletRequestUtils.getIntParameter(request, "channelId", Consts.ZERO);
		Integer featured = ServletRequestUtils.getIntParameter(request, "featured", Consts.ZERO);


		Pageable pageable = wrapPageable(Sort.by(Sort.Direction.DESC, "weight", "created"));
		Page<PostVO> page = postService.paging4Admin(pageable, channelId, title,telephone,ngaykham,featured,diachi);
		model.put("page", page);
		model.put("title", title);
		model.put("telephone",telephone);
		model.put("ngaykham",ngaykham);
		model.put("featured",featured);
		model.put("diachi",diachi);
		model.put("id", id);
		model.put("channelId", channelId);
		model.put("channels", channelService.findAll(Consts.IGNORE));
		return "/admin/post/list";
	}
	
	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String toUpdate(Long id, ModelMap model) {
		String editor = siteOptions.getValue("editor");
		if (null != id && id > 0) {
			PostVO view = postService.get(id);
			if (StringUtils.isNoneBlank(view.getEditor())) {
				editor = view.getEditor();
			}
			model.put("view", view);
		}
		model.put("editor", editor);
		model.put("channels", channelService.findAll(Consts.IGNORE));
		return "/admin/post/view";
	}
	
	/**
	 * @author LBB
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String subUpdate(PostVO post) {
		if (post != null) {
			if (post.getId() > 0) {
				postService.update(post);
			} else {
				AccountProfile profile = getProfile();
				post.setAuthorId(profile.getId());
				postService.post(post);
			}
		}
		return "redirect:/admin/post/list";
	}

	@RequestMapping("/featured")
	@ResponseBody
	public Result featured(Long id, HttpServletRequest request) {
		Result data = Result.failure("lỗi hệ thống");
		int featured = ServletRequestUtils.getIntParameter(request, "featured", Consts.FEATURED_ACTIVE);
		if (id != null) {
			try {
				postService.updateFeatured(id, featured);
				data = Result.success();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}

	@RequestMapping("/weight")
	@ResponseBody
	public Result weight(Long id, HttpServletRequest request) {
		Result data = Result.failure("lỗi hệ thống");
		int weight = ServletRequestUtils.getIntParameter(request, "weight", Consts.FEATURED_ACTIVE);
		if (id != null) {
			try {
				postService.updateWeight(id, weight);
				data = Result.success();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("id") List<Long> id) {
		Result data = Result.failure("lỗi hệ thống");
		if (id != null) {
			try {
				postService.delete(id);
				data = Result.success();
			} catch (Exception e) {
				data = Result.failure(e.getMessage());
			}
		}
		return data;
	}
}
