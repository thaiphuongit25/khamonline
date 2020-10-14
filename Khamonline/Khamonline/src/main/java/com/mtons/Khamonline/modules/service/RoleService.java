package com.mtons.Khamonline.modules.service;

import com.mtons.Khamonline.modules.entity.Permission;
import com.mtons.Khamonline.modules.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author - langhsu on 2018/2/11
 */
public interface RoleService {
    /**
     */
    Page<Role> paging(Pageable pageable, String name);

    /**
     */
    List<Role> list();

    Map<Long, Role> findByIds(Set<Long> ids);

    /**
     */
    Role get(long id);

    /**
     */
    void update(Role r, Set<Permission> permissions);

    /**
     */
    boolean delete(long id);

    /**
     */
    void activate(long id, boolean active);

}
