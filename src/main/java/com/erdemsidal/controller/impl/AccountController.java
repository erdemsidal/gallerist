package com.erdemsidal.controller.impl;


import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.IAccountController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.DtoAccount;
import com.erdemsidal.dto.DtoAccountIU;
import com.erdemsidal.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/account")
public class AccountController extends BaseController implements IAccountController {

    @Autowired
    private IAccountService accountService;


    @Override
    @PostMapping("/save")
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }

    @Override
    @GetMapping("/get/{id}")
    public RootEntity<DtoAccount> getAccountById(@PathVariable("id") Long id) {
        return ok(accountService.getAccountById(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<DtoAccount> deleteAccountById(@PathVariable("id") Long id) {
        return ok(accountService.deleteAccountById(id));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoAccount> updateAccount(@PathVariable("id") Long id,@RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.updateAccount(id,dtoAccountIU));
    }

}
