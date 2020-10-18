package com.mtons.Khamonline.modules.repository;

import com.mtons.Khamonline.modules.entity.TypeChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeChannelRepository extends JpaRepository<TypeChannel, Long>, JpaSpecificationExecutor<TypeChannel> {
}
