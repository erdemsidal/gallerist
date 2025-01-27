package com.erdemsidal.service;

import com.erdemsidal.dto.AuthRequest;
import com.erdemsidal.dto.AuthResponse;
import com.erdemsidal.dto.DtoUser;
import com.erdemsidal.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser reqister(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}
