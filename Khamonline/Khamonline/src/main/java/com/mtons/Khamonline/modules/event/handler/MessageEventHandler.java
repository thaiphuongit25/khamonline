package com.mtons.Khamonline.modules.event.handler;

import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.entity.Comment;
import com.mtons.Khamonline.base.lang.Consts;
import com.mtons.Khamonline.modules.data.MessageVO;
import com.mtons.Khamonline.modules.event.MessageEvent;
import com.mtons.Khamonline.modules.service.CommentService;
import com.mtons.Khamonline.modules.service.MessageService;
import com.mtons.Khamonline.modules.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author langhsu on 2015/8/31.
 */
@Component
public class MessageEventHandler implements ApplicationListener<MessageEvent> {
    @Autowired
    private MessageService messageService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @Async
    @Override
    public void onApplicationEvent(MessageEvent event) {
        MessageVO nt = new MessageVO();
        nt.setPostId(event.getPostId());
        nt.setFromId(event.getFromUserId());
        nt.setEvent(event.getEvent());

        PostVO p;
        switch (event.getEvent()) {
            case Consts.MESSAGE_EVENT_FAVOR_POST:
                p = postService.get(event.getPostId());
                Assert.notNull(p, "Bài báo không tồn tại");
                nt.setUserId(p.getAuthorId());
                break;
            case Consts.MESSAGE_EVENT_COMMENT:
                p = postService.get(event.getPostId());
                Assert.notNull(p, "Bài báo không tồn tại");
                nt.setUserId(p.getAuthorId());
                postService.identityComments(event.getPostId());
                break;
            case Consts.MESSAGE_EVENT_COMMENT_REPLY:
                Comment c = commentService.findById(event.getCommentParentId());
                Assert.notNull(c, "Nhận xét đã trả lời không tồn tại");
                nt.setUserId(c.getAuthorId());

                postService.identityComments(event.getPostId());
                break;
            default:
                nt.setUserId(event.getToUserId());
        }

        messageService.send(nt);
    }
}
