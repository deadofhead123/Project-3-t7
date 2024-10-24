package com.javaweb.repository.custom;

import com.javaweb.entity.RoleEntity;

import java.util.List;

public interface RoleRepositoryCustom {
    List<RoleEntity> findByUserId(Long userId);
}
