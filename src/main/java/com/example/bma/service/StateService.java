package com.example.bma.service;

import com.example.bma.entity.CountryEntity;
import com.example.bma.entity.StateEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.StateRequestModel;
import com.example.bma.models.response.StateResponseModel;
import com.example.bma.repository.CountryRepository;
import com.example.bma.repository.StateRepository;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.DetailsValidationUtility;
import com.example.bma.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public StateResponseModel addNewState(StateRequestModel requestModel) {
        if (!DetailsValidationUtility.isNameValid(requestModel.getCountryName(), false))
            throw new InvalidDataException("Invalid Country Name: "+requestModel.getCountryName());
        if (!DetailsValidationUtility.isNameValid(requestModel.getStateName(), false))
            throw new InvalidDataException("Invalid State Name: "+requestModel.getStateName());

        StateEntity entity = getStateEntityFromModel(requestModel);
        entity.setCreatedBy(entity.getLastUpdatedBy());
        entity.setCreatedOn(entity.getLastUpdatedOn());
        entity = stateRepository.save(entity);

        return getStateResponseModelFromEntity(entity);
    }

    StateEntity getStateEntityByStateNameAndCountryName(String stateName, String countryName) {
        StateEntity state = stateRepository.findStateEntityByStateNameAndCountry(
                stateName, countryService.getCountryEntityByName(countryName));
        if (state == null) throw new InvalidDataException("Invalid State Name: "+stateName);
        else return state;
    }

    private StateResponseModel getStateResponseModelFromEntity(StateEntity entity) {
        StateResponseModel responseModel = new StateResponseModel();
        responseModel.setStateId(entity.getStateId());
        responseModel.setStateName(entity.getStateName());
        responseModel.setCountryName(entity.getCountry().getCountryName());
        return responseModel;
    }

    private StateEntity getStateEntityFromModel(StateRequestModel requestModel) {
        CountryEntity country = countryService.getCountryEntityByName(requestModel.getCountryName());

        if (stateRepository.existsStateEntityByStateNameAndCountry(requestModel.getStateName(), country))
            throw new InformationAlreadyExistsException("State Name Already Exists: "+requestModel.getStateName());

        StateEntity entity = new StateEntity();
        entity.setStateId(generateStateId());
        entity.setStateName(requestModel.getStateName());
        entity.setCountry(country);
        entity.setLastUpdatedOn(BankManagementUtility.getCurrentDateTime());
        entity.setLastUpdatedBy(jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken()));
        return entity;
    }

    private String generateStateId() {
        StringBuilder stateId = new StringBuilder("ST");
        stateId.append(BankManagementUtility.generateIdNumberByDigit(8));
        if (stateRepository.existsById(stateId.toString())) {
            return generateStateId();
        }
        else return stateId.toString();
    }
}
