package com.example.bma.service;

import com.example.bma.entity.CountryEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.models.request.CountryRequestModel;
import com.example.bma.models.response.CountryResponseModel;
import com.example.bma.repository.CountryRepository;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public CountryResponseModel addNewCountry(CountryRequestModel countryRequestModel) {
        if (countryRepository.existsCountryEntityByCountryName(countryRequestModel.getCountryName()))
            throw new InformationAlreadyExistsException("Country With Name Already Exists: "
                    +countryRequestModel.getCountryName());
        if (countryRepository.existsCountryEntityByCountryShortName(countryRequestModel.getCountryShortName()))
            throw new InformationAlreadyExistsException("Country With Short Name Already Exists: "+
                    countryRequestModel.getCountryShortName());
        CountryEntity entity = getCountryEntityFromModel(countryRequestModel);
        entity.setCreatedOn(entity.getLastUpdatedOn());
        entity.setCreatedBy(entity.getLastUpdatedBy());
        entity = countryRepository.save(entity);
        return getCountryResponseModelFromEntity(entity);
    }

    private CountryResponseModel getCountryResponseModelFromEntity(CountryEntity entity) {
        CountryResponseModel responseModel = new CountryResponseModel();
        responseModel.setCountryId(entity.getCountryId());
        responseModel.setCountryName(entity.getCountryName());
        responseModel.setCountryShortName(entity.getCountryShortName());
        return responseModel;
    }

    private CountryEntity getCountryEntityFromModel(CountryRequestModel requestModel) {
        Date currentDateTime = BankManagementUtility.getCurrentDateTime();

        CountryEntity entity = new CountryEntity();
        entity.setCountryId(generateCountryId());
        entity.setCountryName(requestModel.getCountryName());
        entity.setCountryShortName(requestModel.getCountryShortName());
        entity.setLastUpdatedOn(currentDateTime);
        entity.setLastUpdatedBy(jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken()));

        return entity;
    }

    private String generateCountryId() {
        StringBuilder countryId = new StringBuilder("COUN");
        countryId.append(BankManagementUtility.generateIdNumberByDigit(6));
        if (countryRepository.existsById(countryId.toString())) {
            return generateCountryId();
        }
        else return countryId.toString();
    }
}