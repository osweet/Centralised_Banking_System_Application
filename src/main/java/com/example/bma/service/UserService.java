package com.example.bma.service;

import com.example.bma.entity.UserEntity;
import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.exception.NoRecordAvailableException;
import com.example.bma.models.request.UserRequestModel;
import com.example.bma.models.response.UserResponseModel;
import com.example.bma.repository.RoleRepository;
import com.example.bma.repository.UserRepository;
import com.example.bma.util.BankManagementUtility;
import com.example.bma.util.UserDetailsValidationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseModel addNewUser(UserRequestModel userRequestModel) {
        if (userRepository.existsUserEntityByUserContact(userRequestModel.getUserContact())) {
            throw new InformationAlreadyExistsException("Contact Number Already Exists");
        }
        else if (userRepository.existsUserEntityByUserEmail(userRequestModel.getUserEmail())) {
            throw new InformationAlreadyExistsException("Email Already Exists");
        }
        else {
            UserDetailsValidationUtility.isEmailValid(userRequestModel.getUserEmail());
            UserDetailsValidationUtility.isContactNumberValid(userRequestModel.getUserContact());
            UserDetailsValidationUtility.isNameValid(userRequestModel.getUserFirstName(), false);
            UserDetailsValidationUtility.isNameValid(userRequestModel.getUserLastName(), false);
            UserDetailsValidationUtility.isNameValid(userRequestModel.getUserMiddleName(), true);
            UserDetailsValidationUtility.isPasswordValid(userRequestModel.getUserPassword());

            UserEntity newUserEntity = getUserEntityFromModel(userRequestModel);
            return getUserResponseModelFromEntity(userRepository.save(newUserEntity));
        }
    }

    public List<UserResponseModel> getAllUserList(int pageNumber, int PageSize) {
        Pageable pageWithSpecificNumberOfRecord = PageRequest.of(pageNumber, PageSize);
        Page<UserEntity> userEntities = userRepository.findAll(pageWithSpecificNumberOfRecord);
        if (userEntities.isEmpty()) {
            throw new NoRecordAvailableException("No User Record Available");
        }
        else {
            List<UserResponseModel> userResponseModels = new ArrayList<>();
            userEntities.forEach(userEntity ->
                    userResponseModels.add(getUserResponseModelFromEntity(userEntity)));
            return userResponseModels;
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
        if (userRequestModel.getUserMiddleName() == null)
            newUserEntity.setUserMiddleName("");
        else newUserEntity.setUserMiddleName(userRequestModel.getUserMiddleName().trim());
        newUserEntity.setUserLastName(userRequestModel.getUserLastName().trim());
        newUserEntity.setUserContact(userRequestModel.getUserContact());
        newUserEntity.setUserEmail(userRequestModel.getUserEmail().trim());
        newUserEntity.setUserPassword(passwordEncoder.encode(userRequestModel.getUserPassword().trim()));
        newUserEntity.setUserEnabled(false);
        newUserEntity.setLastUpdatedOn(currentDateTime);
        newUserEntity.setLastUpdatedBy(userId);
        newUserEntity.setCreatedOn(currentDateTime);
        newUserEntity.setCreatedBy(userId);

        newUserEntity.setUserTokens(new HashSet<>());
        newUserEntity.setUserRoles(new HashSet<>());
        newUserEntity.getUserRoles().add(roleRepository.findRoleEntityByRoleName("USER"));

        return newUserEntity;
    }

    private String generateUserId() {
        StringBuilder userId = new StringBuilder("USER");
        userId.append(BankManagementUtility.generateNumberIdNumberByDigit(6));
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
