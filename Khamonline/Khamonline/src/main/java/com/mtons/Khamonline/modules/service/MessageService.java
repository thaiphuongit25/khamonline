package com.mtons.Khamonline.modules.service;

import com.mtons.Khamonline.modules.data.MessageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author langhsu
 */
public interface MessageService {

    Page<MessageVO> pagingByUserId(Pageable pageable, long userId);

    /**
     * @param message
     */
    void send(MessageVO message);

    /**
     * @param userId
     * @return
     */
    int unread4Me(long userId);

    /**
     * @param userId
     */
    void readed4Me(long userId);

    /**
     * @param postId
     * @return
     */
    int deleteByPostId(long postId);
}
