package com.example.bma.controller.admin;

import com.example.bma.exception.InformationAlreadyExistsException;
import com.example.bma.exception.InvalidDataException;
import com.example.bma.models.request.BankRequestModel;
import com.example.bma.models.response.BankResponseModel;
import com.example.bma.response.metadata.CommonResponseMetadataWithMessage;
import com.example.bma.response.template.ErrorResponse;
import com.example.bma.response.template.SuccessResponse;
import com.example.bma.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api/admin/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/add")
    public ResponseEntity<?> registerNewBank(@RequestBody BankRequestModel bankRequestModel) {
        try {
            BankResponseModel responseModel = bankService.addNewBank(bankRequestModel);
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.CREATED.value(), "Bank Added");
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(metadata, responseModel));
        }
        catch (InvalidDataException exception) {
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse<>(metadata));
        }
        catch (InformationAlreadyExistsException exception) {
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.CONFLICT.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse<>(metadata));
        }
    }
}
