package com.example.bma.controller.security;

import com.example.bma.models.request.security.JwtAuthenticationRequestModel;
import com.example.bma.models.response.security.JwtAuthenticationResponseModel;
import com.example.bma.service.security.JwtTokenDetailsService;
import com.example.bma.service.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api/")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenDetailsService jwtTokenDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtAuthenticationRequestModel requestModel) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword())
            );
        }
        catch (BadCredentialsException exception) {
            throw new Exception("Invalid Credentials");
        }

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(requestModel.getUsername());
        final String jwtToken = jwtTokenDetailsService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponseModel(jwtToken));
    }
}
