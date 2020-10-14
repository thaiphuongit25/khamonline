package com.mtons.Khamonline.modules.service;

import com.mtons.Khamonline.modules.data.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : langhsu
 * @version : 1.0
 * @date : 2019/1/18
 */
public interface PostSearchService {
    /**
     * @throws Exception
     */
    Page<PostVO> search(Pageable pageable, String term) throws Exception;

    /**
     */
    void resetIndexes();
}
