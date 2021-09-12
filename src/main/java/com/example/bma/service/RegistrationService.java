package com.example.bma.service;

import com.example.bma.entity.UserEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.models.request.UserRequestModel;
import com.example.bma.models.response.UserResponseModel;
import com.example.bma.repository.RoleRepository;
import com.example.bma.repository.UserRepository;
import com.example.bma.util.BankManagementUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserResponseModel addNewUser(UserRequestModel userRequestModel) {
        if (userRepository.existsUserEntityByUserContact(userRequestModel.getUserContact())) {
            throw new InformationAlreadyExistsException("Contact Number Already Exists");
        }
        else if (userRepository.existsUserEntityByUserEmail(userRequestModel.getUserEmail())) {
            throw new InformationAlreadyExistsException("Email Already Exists");
        }
        else {
            UserEntity newUserEntity = getUserEntityFromModel(userRequestModel);
            userRepository.save(newUserEntity);
            return getUserResponseModelFromEntity(newUserEntity);
        }
    }

    private UserResponseModel getUserResponseModelFromEntity(UserEntity existingUserEntity) {
        UserResponseModel responseModel = new UserResponseModel();
        responseModel.setUserId(existingUserEntity.getUserId());
        responseModel.setUserFullName(buildFullNameOfUser(existingUserEntity));
        responseModel.setUserContact(existingUserEntity.getUserContact());
        responseModel.setUserEmail(existingUserEntity.getUserEmail());
        return responseModel;
    }

    private UserEntity getUserEntityFromModel(UserRequestModel userRequestModel) {
        Date currentDateTime = BankManagementUtility.getCurrentDateTime();
        String userId = generateUserId();
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUserId(userId);
        newUserEntity.setUserFirstName(userRequestModel.getUserFirstName().trim());
        newUserEntity.setUserMiddleName(userRequestModel.getUserMiddleName().trim());
        newUserEntity.setUserLastName(userRequestModel.getUserLastName().trim());
        newUserEntity.setUserContact(userRequestModel.getUserContact());
        newUserEntity.setUserEmail(userRequestModel.getUserEmail().trim());
        newUserEntity.setUserPassword(userRequestModel.getUserPassword().trim());
        newUserEntity.getUserRoles().add(roleRepository.findRoleEntityByRoleName("USER"));
        newUserEntity.setLastUpdatedOn(currentDateTime);
        newUserEntity.setLastUpdatedBy(userId);
        newUserEntity.setCreatedOn(currentDateTime);
        newUserEntity.setCreatedBy(userId);
        return newUserEntity;
    }

    private String generateUserId() {
        StringBuilder userId = new StringBuilder("USR");
        userId.append(BankManagementUtility.generateNumberIdNumberByDigit(7));
        if (userRepository.existsUserEntityByUserId(userId.toString())) {
            return generateUserId();
        }
        else return userId.toString();
    }

    private String buildFullNameOfUser(UserEntity userEntity) {
        StringBuilder fullName = new StringBuilder(userEntity.getUserFirstName());
        if (!userEntity.getUserMiddleName().isEmpty()) {
            fullName.append(" ");
            fullName.append(userEntity.getUserMiddleName());
        }
        fullName.append(" ");
        fullName.append(userEntity.getUserLastName());
        return fullName.toString();
    }
}
