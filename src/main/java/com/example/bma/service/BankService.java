package com.example.bma.service;

import com.example.bma.entity.AddressEntity;
import com.example.bma.entity.BankEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.BankRequestModel;
import com.example.bma.models.response.BankResponseModel;
import com.example.bma.repository.BankRepository;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.DetailsValidationUtility;
import com.example.bma.util.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    public BankResponseModel addNewBank(BankRequestModel bankRequestModel) {
        if (!DetailsValidationUtility.isNameValid(bankRequestModel.getBankName(), false))
            throw new InvalidDataException("Invalid Bank Name: "+bankRequestModel.getBankName());
        if (!DetailsValidationUtility.isEmailValid(bankRequestModel.getBankEmail()))
            throw new InvalidDataException("Invalid Bank Email: "+bankRequestModel.getBankEmail());
        if (bankRepository.existsBankEntityByBankName(bankRequestModel.getBankName()))
            throw new InformationAlreadyExistsException("Bank Already Exists: "+bankRequestModel.getBankName());
        if (bankRepository.existsBankEntityByBankEmail(bankRequestModel.getBankEmail()))
            throw new InformationAlreadyExistsException("Email Already Exists: "+bankRequestModel.getBankEmail());

        BankEntity entity = getBankEntityFromModel(bankRequestModel);
        entity.setCreatedOn(entity.getLastUpdatedOn());
        entity.setCreatedBy(entity.getLastUpdatedBy());
        entity = bankRepository.save(entity);
        return getBankResponseModelFromEntity(entity);
    }

    private BankResponseModel getBankResponseModelFromEntity(BankEntity entity) {
        BankResponseModel responseModel = new BankResponseModel();
        responseModel.setBankId(entity.getBankId());
        responseModel.setBankName(entity.getBankName());
        responseModel.setBankEmail(entity.getBankEmail());
        responseModel.setBankAddress(addressService.getAddressResponseModelFromEntity(entity.getBankAddress()));
        return responseModel;
    }

    private BankEntity getBankEntityFromModel(BankRequestModel requestModel) {
        BankEntity entity = new BankEntity();
        entity.setBankId(generateBankId());
        entity.setBankName(requestModel.getBankName());
        entity.setBankEmail(requestModel.getBankEmail());

        AddressEntity address = addressService.getAddressEntityById(
                addressService.addNewAddress(requestModel.getBankAddress()).getAddressId());
        entity.setBankAddress(address);
        entity.setBankBranches(new HashSet<>());
        entity.setBankTokens(new HashSet<>());
        entity.setLastUpdatedOn(BankManagementUtility.getCurrentDateTime());
        entity.setLastUpdatedBy(jwtTokenDetailsService.extractUsername(JwtUtility.getAccessToken()));
        return entity;
    }

    private String generateBankId() {
        StringBuilder bankId = new StringBuilder("BANK");
        bankId.append(BankManagementUtility.generateIdNumberByDigit(6));
        if (bankRepository.existsById(bankId.toString())) {
            return generateBankId();
        }
        else return bankId.toString();
    }
}
