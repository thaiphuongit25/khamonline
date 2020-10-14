package com.mtons.Khamonline.modules.service;

import com.mtons.Khamonline.modules.entity.Permission;
import com.mtons.Khamonline.modules.entity.RolePermission;

import java.util.List;
import java.util.Set;

/**
 * @author - langhsu
 * @create - 2018/5/18
 */
public interface RolePermissionService {
    List<Permission> findPermissions(long roleId);
    void deleteByRoleId(long roleId);
    void add(Set<RolePermission> rolePermissions);

}
