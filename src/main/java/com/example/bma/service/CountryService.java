package com.example.bma.service;

import com.example.bma.entity.CountryEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.CountryRequestModel;
import com.example.bma.models.response.CountryResponseModel;
import com.example.bma.repository.CountryRepository;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.DetailsValidationUtility;
import com.example.bma.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public CountryResponseModel addNewCountry(CountryRequestModel countryRequestModel) {
        if (!DetailsValidationUtility.isNameValid(countryRequestModel.getCountryName(), false))
            throw new InvalidDataException("Invalid Country Name: "+countryRequestModel.getCountryName());
        if (!DetailsValidationUtility.isNameValid(countryRequestModel.getCountryShortName(), false))
            throw new InvalidDataException("Invalid Country Short Name: "+countryRequestModel.getCountryShortName());
        if (countryRepository.existsCountryEntityByCountryName(countryRequestModel.getCountryName()))
            throw new InformationAlreadyExistsException("Country Name Already Exists: "
                    +countryRequestModel.getCountryName());
        if (countryRepository.existsCountryEntityByCountryShortName(countryRequestModel.getCountryShortName()))
            throw new InformationAlreadyExistsException("Country Short Name Already Exists: "+
                    countryRequestModel.getCountryShortName());
        CountryEntity entity = getCountryEntityFromModel(countryRequestModel);
        entity.setCreatedOn(entity.getLastUpdatedOn());
        entity.setCreatedBy(entity.getLastUpdatedBy());
        entity = countryRepository.save(entity);
        return getCountryResponseModelFromEntity(entity);
    }

    CountryEntity getCountryEntityByName(String name) {
        CountryEntity country = countryRepository.findCountryEntityByCountryName(name);
        if (country == null) throw new InvalidDataException("Invalid Country Name: "+name);
        else return country;
    }

    private CountryResponseModel getCountryResponseModelFromEntity(CountryEntity entity) {
        CountryResponseModel responseModel = new CountryResponseModel();
        responseModel.setCountryId(entity.getCountryId());
        responseModel.setCountryName(entity.getCountryName());
        responseModel.setCountryShortName(entity.getCountryShortName());
        return responseModel;
    }

    private CountryEntity getCountryEntityFromModel(CountryRequestModel requestModel) {
        CountryEntity entity = new CountryEntity();
        entity.setCountryId(generateCountryId());
        entity.setCountryName(requestModel.getCountryName());
        entity.setCountryShortName(requestModel.getCountryShortName());
        entity.setLastUpdatedOn(BankManagementUtility.getCurrentDateTime());
        entity.setLastUpdatedBy(jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken()));
        return entity;
    }

    private String generateCountryId() {
        StringBuilder countryId = new StringBuilder("CNTR");
        countryId.append(BankManagementUtility.generateIdNumberByDigit(6));
        if (countryRepository.existsById(countryId.toString())) {
            return generateCountryId();
        }
        else return countryId.toString();
    }
}