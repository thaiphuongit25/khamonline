/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.Khamonline.modules.repository;

import com.mtons.Khamonline.modules.entity.Channel;
import com.mtons.Khamonline.modules.entity.Post;
import org.jboss.logging.annotations.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author langhsu
 */
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {


    /**
     *
     * @return
     */
    Page<Post> findAllByAuthorId(Pageable pageable, long authorId);

    @Query("select coalesce(max(weight), 0) from Post")
    int maxWeight();

    @Modifying
    @Query("update Post set views = views + :increment where id = :id")
    void updateViews(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query("update Post set favors = favors + :increment where id = :id")
    void updateFavors(@Param("id") long id, @Param("increment") int increment);

    @Modifying
    @Query("update Post set comments = comments + :increment where id = :id")
    void updateComments(@Param("id") long id, @Param("increment") int increment);

}
