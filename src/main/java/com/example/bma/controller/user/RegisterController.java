package com.example.bma.controller.user;

import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.UserRequestModel;
import com.example.bma.models.response.UserResponseModel;
import com.example.bma.response.metadata.CommonResponseMetadataWithMessage;
import com.example.bma.response.template.ErrorResponse;
import com.example.bma.response.template.SuccessResponse;
import com.example.bma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> addNewUser(@RequestBody UserRequestModel userRequestModel) {
        try {
            UserResponseModel responseModel = userService.addNewUser(userRequestModel);
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.CREATED.value(), "Added New User");
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(metadata, responseModel));
        }
        catch (InformationAlreadyExistsException exception) {
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.CONFLICT.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse<>(metadata));
        }
        catch (InvalidDataException exception) {
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse<>(metadata));
        }
    }
}
