package com.example.bma.service;

import com.example.bma.entity.CityEntity;
import com.example.bma.entity.StateEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.CityRequestModel;
import com.example.bma.models.response.CityResponseModel;
import com.example.bma.repository.CityRepository;
import com.example.bma.repository.StateRepository;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.DetailsValidationUtility;
import com.example.bma.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public CityResponseModel addNewCity(CityRequestModel requestModel) {
        if (!DetailsValidationUtility.isNameValid(requestModel.getCityName(), false))
            throw new InvalidDataException("Invalid City Name: "+requestModel.getCityName());

        CityEntity entity = getCityEntityFromModel(requestModel);
        entity.setCreatedOn(entity.getLastUpdatedOn());
        entity.setCreatedBy(entity.getLastUpdatedBy());
        entity = cityRepository.save(entity);
        return getCityResponseModelFromEntity(entity);
    }

    private CityResponseModel getCityResponseModelFromEntity(CityEntity entity) {
        CityResponseModel responseModel = new CityResponseModel();
        responseModel.setCityId(entity.getCityId());
        responseModel.setCityName(entity.getCityName());
        responseModel.setStateName(entity.getState().getStateName());
        return responseModel;
    }

    private CityEntity getCityEntityFromModel(CityRequestModel requestModel) {
        StateEntity state = stateRepository.findStateEntityByStateName(requestModel.getStateName());
        if (state == null) throw new InvalidDataException("Invalid State Name: "+requestModel.getStateName());

        if (cityRepository.existsCityEntityByCityNameAndState(requestModel.getCityName(), state))
            throw new InformationAlreadyExistsException("City Name Already Exists: "+requestModel.getCityName());

        CityEntity entity = new CityEntity();
        entity.setCityId(generateCityId());
        entity.setCityName(requestModel.getCityName());
        entity.setState(state);
        entity.setLastUpdatedOn(BankManagementUtility.getCurrentDateTime());
        entity.setLastUpdatedBy(jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken()));

        return entity;
    }

    private String generateCityId() {
        StringBuilder cityId = new StringBuilder("CT");
        cityId.append(BankManagementUtility.generateIdNumberByDigit(8));
        if (cityRepository.existsById(cityId.toString())) {
            return generateCityId();
        }
        else return cityId.toString();
    }
}
