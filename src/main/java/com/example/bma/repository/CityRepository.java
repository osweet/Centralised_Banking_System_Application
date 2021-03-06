package com.example.bma.repository;

import com.example.bma.entity.CityEntity;
import com.example.bma.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, String> {

    boolean existsCityEntityByCityNameAndState(String cityName, StateEntity state);

    CityEntity findCityEntityByCityNameAndState(String name, StateEntity state);
}
