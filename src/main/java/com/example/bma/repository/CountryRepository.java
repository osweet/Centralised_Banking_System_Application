package com.example.bma.repository;

import com.example.bma.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {

    Boolean existsCountryEntityByCountryName(String name);
    Boolean existsCountryEntityByCountryShortName(String shortName);

    CountryEntity findCountryEntityByCountryName(String name);
}
