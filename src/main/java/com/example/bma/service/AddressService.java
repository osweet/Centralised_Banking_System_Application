package com.example.bma.service;

import com.example.bma.entity.AddressEntity;
import com.example.bma.entity.CityEntity;
import com.example.bma.entity.CountryEntity;
import com.example.bma.entity.StateEntity;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.AddressRequestModel;
import com.example.bma.models.response.AddressResponseModel;
import com.example.bma.repository.AddressRepository;
import com.example.bma.repository.CityRepository;
import com.example.bma.repository.CountryRepository;
import com.example.bma.repository.StateRepository;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.DetailsValidationUtility;
import com.example.bma.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public AddressResponseModel addNewAddress(AddressRequestModel addressRequestModel) {
        // TODO: Add Validation to check if address contains any special characters
        if (DetailsValidationUtility.isTextEmpty(addressRequestModel.getLine()))
            throw new InvalidDataException("Address Line Can't be Empty!");
        if (DetailsValidationUtility.isTextEmpty(addressRequestModel.getLandmark()))
            throw new InvalidDataException("Address Landmark Can't Be Empty");
        if (!DetailsValidationUtility.isNameValid(addressRequestModel.getCity(), false))
            throw new InvalidDataException("Invalid City Name: "+addressRequestModel.getCity());
        if (!DetailsValidationUtility.isNameValid(addressRequestModel.getState(), false))
            throw new InvalidDataException("Invalid State Name: "+addressRequestModel.getState());
        if (!DetailsValidationUtility.isNameValid(addressRequestModel.getCountry(), false))
            throw new InvalidDataException("Invalid Country Name: "+addressRequestModel.getCountry());
        if (!DetailsValidationUtility.isZipCodeValid(addressRequestModel.getZipcode()))
            throw new InvalidDataException("Invalid Zip Code: "+addressRequestModel.getZipcode());

        AddressEntity entity = getAddressEntityFromModel(addressRequestModel);
        entity.setCreatedBy(entity.getLastUpdatedBy());
        entity.setCreatedOn(entity.getLastUpdatedOn());
        entity = addressRepository.save(entity);
        return getAddressResponseModelFromEntity(entity);
    }

    AddressEntity getAddressEntityById(String addressId) {
        AddressEntity entity = addressRepository.findAddressEntityByAddressId(addressId);
        if (entity == null) throw new InvalidDataException("Invalid Address ID: "+addressId);
        else return entity;
    }

    AddressResponseModel getAddressResponseModelFromEntity(AddressEntity entity) {
        AddressResponseModel responseModel = new AddressResponseModel();
        responseModel.setAddressId(entity.getAddressId());
        responseModel.setAddressLine(entity.getAddressLine());
        responseModel.setLandmark(entity.getAddressLandmark());
        responseModel.setCity(entity.getAddressCity().getCityName());
        responseModel.setState(entity.getAddressCity().getState().getStateName());
        responseModel.setCountry(entity.getAddressCity().getState().getCountry().getCountryName());
        responseModel.setZipcode(entity.getAddressZipCode());
        return responseModel;
    }

    AddressEntity getAddressEntityFromModel(AddressRequestModel addressRequestModel) {
        AddressEntity entity = new AddressEntity();
        entity.setAddressId(generateAddressId());
        entity.setAddressLine(addressRequestModel.getLine());
        entity.setAddressLandmark(addressRequestModel.getLandmark());

        CityEntity city = cityService.getCityEntityByCityAndStateAndCountryName(
                addressRequestModel.getCity(), addressRequestModel.getState(), addressRequestModel.getCountry());
        entity.setAddressCity(city);
        entity.setAddressZipCode(addressRequestModel.getZipcode());
        entity.setAddressVerified(false);
        entity.setLastUpdatedOn(BankManagementUtility.getCurrentDateTime());
        entity.setLastUpdatedBy(jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken()));

        return entity;
    }

    private String generateAddressId() {
        StringBuilder addressId = new StringBuilder("ADR");
        addressId.append(BankManagementUtility.generateIdNumberByDigit(7));
        if (addressRepository.existsById(addressId.toString())) {
            return generateAddressId();
        }
        else return addressId.toString();
    }
}
