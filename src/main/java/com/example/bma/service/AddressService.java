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
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public AddressResponseModel addNewAddress(AddressRequestModel addressRequestModel) {
        //TODO: Move the below numeric validation to validation package
        if (addressRequestModel.getBuildingNumber()!=null
                && (addressRequestModel.getBuildingNumber()<1 || addressRequestModel.getBuildingNumber()>999))
            throw new InvalidDataException("Invalid Building Number: "+addressRequestModel.getBuildingNumber());
        if (DetailsValidationUtility.isTextEmpty(addressRequestModel.getLine1()))
            throw new InvalidDataException("First Line of Address Can't be Empty!");
        if (DetailsValidationUtility.isTextEmpty(addressRequestModel.getLandmark()))
            throw new InvalidDataException("Address Landmark Can't Be Empty");
        // TODO: Add Validation to check if address contains any special characters
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
        responseModel.setAddressLine(buildCompleteAddress(entity));
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
        entity.setAddressBuildingNumber(addressRequestModel.getBuildingNumber());
        entity.setAddressLine1(addressRequestModel.getLine1());
        entity.setAddressLine2(addressRequestModel.getLine2());
        entity.setAddressLine3(addressRequestModel.getLine3());
        entity.setAddressLandmark(addressRequestModel.getLandmark());

        CountryEntity country = countryRepository.findCountryEntityByCountryName(addressRequestModel.getCountry());
        if (country == null) throw new InvalidDataException("Invalid Country Name: "+addressRequestModel.getCountry());

        StateEntity state = stateRepository.findStateEntityByStateNameAndCountry(
                addressRequestModel.getState(), country);
        if (state == null) throw new InvalidDataException("Invalid State Name: "+addressRequestModel.getState());

        CityEntity city = cityRepository.findCityEntityByCityNameAndState(addressRequestModel.getCity(), state);
        if (city == null) throw new InvalidDataException("Invalid City Name: "+addressRequestModel.getCity());

        entity.setAddressCity(city);
        entity.setAddressZipCode(addressRequestModel.getZipcode());
        entity.setAddressVerified(false);
        entity.setLastUpdatedOn(BankManagementUtility.getCurrentDateTime());
        entity.setLastUpdatedBy(jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken()));

        return entity;
    }

    private String buildCompleteAddress(AddressEntity entity) {
        StringBuilder completeAddress = new StringBuilder();

        if (entity.getAddressBuildingNumber() != null)
            completeAddress.append(Integer.toString(entity.getAddressBuildingNumber())).append(", ");

        completeAddress.append(entity.getAddressLine1()).append(", ");
        if (entity.getAddressLine2() != null)
            completeAddress.append(entity.getAddressLine2()).append(", ");
        if (entity.getAddressLine3() != null)
            completeAddress.append(entity.getAddressLine3()).append(", ");

        return completeAddress.toString();
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
