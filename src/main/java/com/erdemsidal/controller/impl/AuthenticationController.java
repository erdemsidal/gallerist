package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.IAuthenticationController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.AuthRequest;
import com.erdemsidal.dto.AuthResponse;
import com.erdemsidal.dto.DtoUser;
import com.erdemsidal.dto.RefreshTokenRequest;
import com.erdemsidal.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController extends BaseController implements IAuthenticationController {


    @Autowired
    private IAuthenticationService authenticationService;


    @Override
    @PostMapping("/register")
    public RootEntity<DtoUser> reqister(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.reqister(input));
    }

    @Override
    @PostMapping("/authenticate")
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.authenticate(input));
    }

    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
        return ok(authenticationService.refreshToken(input));
    }
}
