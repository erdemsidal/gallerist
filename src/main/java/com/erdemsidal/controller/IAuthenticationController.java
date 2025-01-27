package com.erdemsidal.controller;

import com.erdemsidal.dto.*;
import jakarta.persistence.criteria.Root;

public interface IAuthenticationController {

    public RootEntity<DtoUser> reqister(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);


}
