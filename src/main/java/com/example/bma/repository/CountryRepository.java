package com.example.bma.repository;

import com.example.bma.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {

    boolean existsCountryEntityByCountryName(String name);
    boolean existsCountryEntityByCountryShortName(String shortName);

    CountryEntity findCountryEntityByCountryName(String name);
}
