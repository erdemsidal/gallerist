package com.erdemsidal.service.impl;

import com.erdemsidal.dto.AuthRequest;
import com.erdemsidal.dto.AuthResponse;
import com.erdemsidal.dto.DtoUser;
import com.erdemsidal.dto.RefreshTokenRequest;
import com.erdemsidal.entity.RefreshToken;
import com.erdemsidal.entity.User;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.jwt.JwtService;
import com.erdemsidal.repository.RefreshTokenRepository;
import com.erdemsidal.repository.UserRepository;
import com.erdemsidal.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    private User createUser(AuthRequest input){
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
        return user;
    }



    public RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *4));
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }


    @Override
    public DtoUser reqister(AuthRequest input) {

        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser,dtoUser);

        return dtoUser;
    }




    @Override
    public AuthResponse authenticate(AuthRequest input) {

        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(input.getUsername(),input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(input.getUsername());


            String accessToken = jwtService.generateToken(optUser.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optUser.get()));

            return  new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    public boolean isValidRefreshToken(Date expireDate){
        return new Date().before(expireDate);
    }


    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {

        Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());

        if(optRefreshToken.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND,input.getRefreshToken()));
        }

        if (!isValidRefreshToken(optRefreshToken.get().getExpireDate())){
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED,input.getRefreshToken()));
        }

        User user = optRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = createRefreshToken(user);
        RefreshToken savedRefreshToken =  refreshTokenRepository.save(refreshToken);

        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
    }


}
