package com.mtons.Khamonline.modules.repository;

import com.mtons.Khamonline.modules.entity.PostResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author saxing 2019/4/5 8:09
 */
public interface PostResourceRepository extends JpaRepository<PostResource, Long>, JpaSpecificationExecutor<PostResource> {

    int deleteByPostId(long postId);

    int deleteByPostIdAndResourceIdIn(long postId, Collection<Long> resourceId);

    List<PostResource> findByResourceId(long resourceId);

    List<PostResource> findByPostId(long postId);

}
