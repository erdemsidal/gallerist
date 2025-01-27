package com.erdemsidal.controller;


import com.erdemsidal.dto.DtoAccount;
import com.erdemsidal.dto.DtoAccountIU;

public interface IAccountController {


    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

    public RootEntity<DtoAccount> getAccountById(Long id);

    public RootEntity<DtoAccount> deleteAccountById(Long id);

    public RootEntity<DtoAccount> updateAccount(Long id , DtoAccountIU dtoAccountIU);


}
