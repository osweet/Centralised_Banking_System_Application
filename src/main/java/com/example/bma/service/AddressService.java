package com.example.bma.service;

import com.example.bma.entity.AddressEntity;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.common.AddressModel;
import com.example.bma.repository.AddressRepository;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.DetailsValidationUtility;
import com.example.bma.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public AddressModel addNewAddress(AddressModel addressModel) {
        if (DetailsValidationUtility.isTextEmpty(addressModel.getLine1()))
            throw new InvalidDataException("First Line of Address Can't be Empty!");

        return addressModel;
    }

    AddressEntity getAddressEntityFromModel(AddressModel addressModel) {
        Date currentDateTime = BankManagementUtility.getCurrentDateTime();
        String adminId = jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken());

        AddressEntity entity = new AddressEntity();

        entity.setAddressId(generateAddressId());
        entity.setAddressLine1(addressModel.getLine1());
        entity.setAddressLine2(addressModel.getLine2());
        entity.setAddressLine3(addressModel.getLine3());
        entity.setAddressLandmark(addressModel.getLandmark());
        entity.setAddressCity(addressModel.getCity());
        entity.setAddressState(addressModel.getState());
        entity.setAddressCountry(addressModel.getCountry());
        entity.setAddressZipCode(addressModel.getZipcode());
        entity.setLastUpdatedOn(currentDateTime);
        entity.setLastUpdatedBy(adminId);
        entity.setCreatedOn(currentDateTime);
        entity.setCreatedBy(adminId);

        return entity;
    }

    private String generateAddressId() {
        StringBuilder addressId = new StringBuilder("ADDR");
        addressId.append(BankManagementUtility.generateIdNumberByDigit(6));
        if (addressRepository.existsById(addressId.toString())) {
            return generateAddressId();
        }
        else return addressId.toString();
    }
}
