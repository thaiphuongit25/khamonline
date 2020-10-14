package com.mtons.Khamonline.modules.service;

import com.mtons.Khamonline.modules.data.FavoriteVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author langhsu
 */
public interface FavoriteService {
    /**
     * @param pageable
     * @param userId
     * @return
     */
    Page<FavoriteVO> pagingByUserId(Pageable pageable, long userId);

    void add(long userId, long postId);
    void delete(long userId, long postId);
    void deleteByPostId(long postId);
}
