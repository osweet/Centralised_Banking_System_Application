package com.example.bma.repository;

import com.example.bma.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findUserEntityByUserEmail(String email);
    UserEntity findUserEntityByUserId(String id);

    Boolean existsUserEntityByUserId(String id);
    Boolean existsUserEntityByUserEmail(String email);
    Boolean existsUserEntityByUserContact(Long contact);
}
