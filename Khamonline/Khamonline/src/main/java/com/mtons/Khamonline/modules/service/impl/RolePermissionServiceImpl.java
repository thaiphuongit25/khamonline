package com.mtons.Khamonline.modules.service.impl;

import com.mtons.Khamonline.modules.entity.Permission;
import com.mtons.Khamonline.modules.entity.RolePermission;
import com.mtons.Khamonline.modules.repository.PermissionRepository;
import com.mtons.Khamonline.modules.repository.RolePermissionRepository;
import com.mtons.Khamonline.modules.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author - langhsu
 * @create - 2018/5/18
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Permission> findPermissions(long roleId) {
        List<RolePermission> rps = rolePermissionRepository.findAllByRoleId(roleId);

        List<Permission> rets = null;
        if (rps != null && rps.size() > 0) {
            Set<Long> pids = new HashSet<>();
            rps.forEach(rp -> pids.add(rp.getPermissionId()));
            rets = permissionRepository.findAllById(pids);
        }
        return rets;
    }

    @Override
    @Transactional
    public void deleteByRoleId(long roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    @Override
    @Transactional
    public void add(Set<RolePermission> rolePermissions) {
        rolePermissionRepository.saveAll(rolePermissions);
    }
}
