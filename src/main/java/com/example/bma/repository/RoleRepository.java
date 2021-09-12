package com.example.bma.repository;

import com.example.bma.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    RoleEntity findRoleEntityByRoleName(String name);

    Boolean existsRoleEntityByRoleId(String id);
    Boolean existsRoleEntityByRoleName(String name);
}
