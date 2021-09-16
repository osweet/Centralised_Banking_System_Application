package com.example.bma.controller.admin;

import com.example.bma.exception.NoRecordAvailableException;
import com.example.bma.models.response.UserResponseModel;
import com.example.bma.response.metadata.CommonResponseMetadataWithMessage;
import com.example.bma.response.metadata.GetAllDataResponseMetadata;
import com.example.bma.response.template.ErrorResponse;
import com.example.bma.response.template.SuccessResponse;
import com.example.bma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bma/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_PAGE_SIZE = 5;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(
            @RequestParam(name = "page_number", required = false) Integer pageNumber,
            @RequestParam(name = "page_size", required = false) Integer pageSize) {
        try {
            if (pageNumber == null) pageNumber = DEFAULT_PAGE_NUMBER;
            if (pageSize == null) pageSize = DEFAULT_PAGE_SIZE;
            List<UserResponseModel> responseModels = userService.getAllUserList(pageNumber, pageSize);
            GetAllDataResponseMetadata metadata = new GetAllDataResponseMetadata(HttpStatus.FOUND.value(),
                    pageNumber+1, pageSize);
            return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>(metadata, responseModels));
        }
        catch (NoRecordAvailableException exception) {
            CommonResponseMetadataWithMessage metadata =
                    new CommonResponseMetadataWithMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse<>(metadata));
        }
    }
}
