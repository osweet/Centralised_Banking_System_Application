package com.example.bma.repository;

import com.example.bma.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {

    AddressEntity findAddressEntityByAddressId(String id);
}
