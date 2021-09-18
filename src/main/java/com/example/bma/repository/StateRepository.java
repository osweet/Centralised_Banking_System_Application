package com.example.bma.repository;

import com.example.bma.entity.CountryEntity;
import com.example.bma.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, String> {

    Boolean existsStateEntityByStateNameAndCountry(String stateName, CountryEntity country);

    StateEntity findStateEntityByStateName(String name);
}
