package com.mtons.Khamonline.modules.service;

import com.mtons.Khamonline.modules.entity.Permission;
import com.mtons.Khamonline.modules.data.PermissionTree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author - langhsu on 2018/2/11
 */
public interface PermissionService {
    /**
     */
    Page<Permission> paging(Pageable pageable, String name);

    /**
     */
    List<PermissionTree> tree();

    /**
     */
    List<PermissionTree> tree(int parentId);

    /**
     */
    List<Permission> list();

    /**
     */
    Permission get(long id);

}
