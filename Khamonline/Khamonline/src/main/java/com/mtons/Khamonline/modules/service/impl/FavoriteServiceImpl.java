package com.mtons.Khamonline.modules.service.impl;

import com.mtons.Khamonline.modules.data.FavoriteVO;
import com.mtons.Khamonline.modules.data.PostVO;
import com.mtons.Khamonline.modules.entity.Favorite;
import com.mtons.Khamonline.modules.repository.FavoriteRepository;
import com.mtons.Khamonline.base.utils.BeanMapUtils;
import com.mtons.Khamonline.modules.service.FavoriteService;
import com.mtons.Khamonline.modules.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author langhsu on 2015/8/31.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private PostService postService;

    @Override
    public Page<FavoriteVO> pagingByUserId(Pageable pageable, long userId) {
        Page<Favorite> page = favoriteRepository.findAllByUserId(pageable, userId);

        List<FavoriteVO> rets = new ArrayList<>();
        Set<Long> postIds = new HashSet<>();
        for (Favorite po : page.getContent()) {
            rets.add(BeanMapUtils.copy(po));
            postIds.add(po.getPostId());
        }

        if (postIds.size() > 0) {
            Map<Long, PostVO> posts = postService.findMapByIds(postIds);

            for (FavoriteVO t : rets) {
                PostVO p = posts.get(t.getPostId());
                t.setPost(p);
            }
        }
        return new PageImpl<>(rets, pageable, page.getTotalElements());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void add(long userId, long postId) {
        Favorite po = favoriteRepository.findByUserIdAndPostId(userId, postId);

        Assert.isNull(po, "Bạn đã đánh dấu bài viết này");

        po = new Favorite();
        po.setUserId(userId);
        po.setPostId(postId);
        po.setCreated(new Date());

        favoriteRepository.save(po);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(long userId, long postId) {
        Favorite po = favoriteRepository.findByUserIdAndPostId(userId, postId);
        Assert.notNull(po, "Chưa thích bài viết này");
        favoriteRepository.delete(po);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteByPostId(long postId) {
        int rows = favoriteRepository.deleteByPostId(postId);
        log.info("favoriteRepository delete {}", rows);
    }

}
