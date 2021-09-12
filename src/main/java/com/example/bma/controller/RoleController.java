package com.example.bma.controller;

import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.models.request.RoleRequestModel;
import com.example.bma.models.response.RoleResponseModel;
import com.example.bma.models.response.UserResponseModel;
import com.example.bma.response.ErrorResponse;
import com.example.bma.response.ResponseMetadata;
import com.example.bma.response.ResponsePayload;
import com.example.bma.response.SuccessResponse;
import com.example.bma.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewRole(@RequestBody RoleRequestModel roleRequestModel) {
        try {
            RoleResponseModel responseModel = roleService.addNewRole(roleRequestModel);
            ResponseMetadata metadata = new ResponseMetadata(HttpStatus.CREATED.value(),
                    "Added New Role");
            ResponsePayload<RoleResponseModel> payload = new ResponsePayload<>(responseModel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponse<>(metadata, payload));
        }
        catch (InformationAlreadyExistsException exception) {
            ResponseMetadata metadata = new ResponseMetadata(HttpStatus.CONFLICT.value(),
                    exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(metadata));
        }
    }
}
