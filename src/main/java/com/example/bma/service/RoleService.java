package com.example.bma.service;

import com.example.bma.entity.RoleEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.models.request.RoleRequestModel;
import com.example.bma.models.response.RoleResponseModel;
import com.example.bma.repository.RoleRepository;
import com.example.bma.util.BankManagementUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleResponseModel addNewRole(RoleRequestModel requestModel) {
        if (roleRepository.existsRoleEntityByRoleName(requestModel.getRoleName())) {
            throw new InformationAlreadyExistsException("Role Name Already Exists");
        }
        else {
            RoleEntity newRoleEntity = getRoleEntityFromModel(requestModel);
            roleRepository.save(newRoleEntity);
            return getRoleResponseModelFromEntity(newRoleEntity);
        }
    }

    private RoleResponseModel getRoleResponseModelFromEntity(RoleEntity roleEntity) {
        RoleResponseModel responseModel = new RoleResponseModel();
        responseModel.setRoleId(roleEntity.getRoleId());
        responseModel.setRoleName(roleEntity.getRoleName());
        responseModel.setRoleDescription(roleEntity.getRoleDescription());
        return responseModel;
    }

    private RoleEntity getRoleEntityFromModel(RoleRequestModel requestModel) {
        Date currentDateTime = BankManagementUtility.getCurrentDateTime();
        String roleId = generateRoleId();
        RoleEntity newRoleEntity = new RoleEntity();
        newRoleEntity.setRoleId(roleId);
        newRoleEntity.setRoleName(requestModel.getRoleName());
        newRoleEntity.setRoleDescription(requestModel.getRoleDescription());
        newRoleEntity.setLastUpdatedOn(currentDateTime);
        newRoleEntity.setCreatedOn(currentDateTime);
        newRoleEntity.setLastUpdatedBy("user_id");
        newRoleEntity.setCreatedBy("user_id");
        return newRoleEntity;
    }

    private String generateRoleId() {
        StringBuilder roleId = new StringBuilder("ROLE");
        roleId.append(BankManagementUtility.generateNumberIdNumberByDigit(6));
        if (roleRepository.existsRoleEntityByRoleId(roleId.toString())) {
            return generateRoleId();
        }
        else return roleId.toString();
    }
}
