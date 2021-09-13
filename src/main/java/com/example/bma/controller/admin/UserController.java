package com.example.bma.controller.admin;

import com.example.bma.exception.NoRecordAvailableException;
import com.example.bma.models.response.UserResponseModel;
import com.example.bma.response.ErrorResponse;
import com.example.bma.response.ResponseMetadata;
import com.example.bma.response.ResponsePayload;
import com.example.bma.response.SuccessResponse;
import com.example.bma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bma/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserResponseModel> responseModels = userService.getAllUserList();
            ResponseMetadata metadata = new ResponseMetadata(HttpStatus.FOUND.value(),
                    "Total Number of Record Found: "+responseModels.size());
            ResponsePayload<List<UserResponseModel>> payload = new ResponsePayload<>(responseModels);
            return ResponseEntity.status(HttpStatus.FOUND).body(new SuccessResponse<>(metadata, payload));
        }
        catch (NoRecordAvailableException exception) {
            ResponseMetadata metadata =
                    new ResponseMetadata(HttpStatus.NOT_FOUND.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(metadata));
        }
    }
}
