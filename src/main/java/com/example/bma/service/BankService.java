package com.example.bma.service;

import com.example.bma.entity.BankEntity;
import com.example.bma.models.request.BankRequestModel;
import com.example.bma.repository.BankRepository;
import com.example.bma.util.BankManagementUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void addNewBank(BankRequestModel bankRequestModel) {
    }

    private BankEntity getBankEntityFromModel(BankRequestModel requestModel) {
        Date currentDateTime = BankManagementUtility.getCurrentDateTime();

        BankEntity entity = new BankEntity();
        entity.setBankId(generateBankId());
        entity.setBankName(requestModel.getBankName());
        entity.setBankEmail(requestModel.getBankEmail());
        entity.setBankBranches(new HashSet<>());
        entity.setBankTokens(new HashSet<>());
        entity.setLastUpdatedOn(currentDateTime);
        entity.setCreatedOn(currentDateTime);

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
