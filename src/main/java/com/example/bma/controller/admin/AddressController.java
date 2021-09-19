package com.example.bma.controller.admin;

import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.AddressRequestModel;
import com.example.bma.models.response.AddressResponseModel;
import com.example.bma.response.metadata.CommonResponseMetadataWithMessage;
import com.example.bma.response.template.ErrorResponse;
import com.example.bma.response.template.SuccessResponse;
import com.example.bma.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api/admin/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewAddress(@RequestBody AddressRequestModel addressRequestModel) {
        try {
            AddressResponseModel responseModel = addressService.addNewAddress(addressRequestModel);
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.CREATED.value(), "Address Added");
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(metadata, responseModel));
        }
        catch (InvalidDataException exception) {
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse<>(metadata));
        }
    }
}
