package com.example.bma.repository;

import com.example.bma.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankEntity, String> {

    Boolean existsBankEntityByBankName(String name);
    Boolean existsBankEntityByBankEmail(String email);
}
