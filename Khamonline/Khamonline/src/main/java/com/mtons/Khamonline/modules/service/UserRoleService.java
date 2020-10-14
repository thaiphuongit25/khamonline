package com.mtons.Khamonline.modules.service;

import com.mtons.Khamonline.modules.entity.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author - langhsu on 2018/2/11
 */
public interface UserRoleService {
    /**
     * @return
     */
    List<Long> listRoleIds(long userId);

    /**
     */
    List<Role> listRoles(long userId);

    Map<Long, List<Role>> findMapByUserIds(List<Long> userIds);

    /**
     */
    void updateRole(long userId, Set<Long> roleIds);
}
