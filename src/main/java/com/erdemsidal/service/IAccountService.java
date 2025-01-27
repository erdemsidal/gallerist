package com.erdemsidal.service;

import com.erdemsidal.dto.DtoAccount;
import com.erdemsidal.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

    public DtoAccount getAccountById(Long id);

    public DtoAccount deleteAccountById(Long id);

    public DtoAccount updateAccount(Long id , DtoAccountIU dtoAccountIU);

}
