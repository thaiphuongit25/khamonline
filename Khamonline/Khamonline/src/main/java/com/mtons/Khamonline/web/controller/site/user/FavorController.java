package com.mtons.Khamonline.web.controller.site.user;

import com.mtons.Khamonline.modules.event.MessageEvent;
import com.mtons.Khamonline.modules.service.PostService;
import com.mtons.Khamonline.base.lang.Result;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.modules.data.AccountProfile;
import com.mtons.Khamonline.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author langhsu
 */
@RestController
@RequestMapping("/user")
public class FavorController extends BaseController {
    @Autowired
    private PostService postService;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * @param id
     * @return
     */
    @RequestMapping("/favor")
    public Result favor(Long id) {
        Result data = Result.failure("lỗi hệ thống");
        if (id != null) {
            try {
                AccountProfile up = getProfile();
                postService.favor(up.getId(), id);
                sendMessage(up.getId(), id);
                data = Result.success();
            } catch (Exception e) {
                data = Result.failure(e.getMessage());
            }
        }
        return data;
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/unfavor")
    public Result unfavor(Long id) {
        Result data = Result.failure("lỗi hệ thống");
        if (id != null) {
            try {
                AccountProfile up = getProfile();
                postService.unfavor(up.getId(), id);
                data = Result.success();
            } catch (Exception e) {
                data = Result.failure(e.getMessage());
            }
        }
        return data;
    }

    /**
     * @param userId
     * @param postId
     */
    private void sendMessage(long userId, long postId) {
        MessageEvent event = new MessageEvent("MessageEvent" + System.currentTimeMillis());
        event.setFromUserId(userId);
        event.setEvent(Consts.MESSAGE_EVENT_FAVOR_POST);
        event.setPostId(postId);
        applicationContext.publishEvent(event);
    }
}
