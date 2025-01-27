package com.erdemsidal.service.impl;

import com.erdemsidal.dto.DtoAccount;
import com.erdemsidal.dto.DtoAccountIU;
import com.erdemsidal.entity.Account;
import com.erdemsidal.entity.Customer;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.repository.AccountRepository;
import com.erdemsidal.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountServiceImpl  implements IAccountService {


    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU){

        Account account = new Account();
        account.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAccountIU,account);
        return account;
    }


    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {

        DtoAccount dtoAccount = new DtoAccount();

        Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));

        BeanUtils.copyProperties(savedAccount,dtoAccount);

        return dtoAccount;
    }

    @Override
    public DtoAccount getAccountById(Long id) {

        DtoAccount dtoAccount = new DtoAccount();

        Optional<Account> optAccount = accountRepository.findById(id);

        if (optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Account account = optAccount.get();

        BeanUtils.copyProperties(account,dtoAccount);

        return dtoAccount;
    }

    @Override
    public DtoAccount deleteAccountById(Long id) {

        DtoAccount dtoAccount = new DtoAccount();

        Optional<Account> optAccount = accountRepository.findById(id);

        if (optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Account account = optAccount.get();

        BeanUtils.copyProperties(account,dtoAccount);

        dtoAccount.setDescription(id +" numaralı account silinmiştir.");

        accountRepository.delete(account);

        return dtoAccount;
    }

    @Override
    public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU) {

        DtoAccount dtoAccount = new DtoAccount();

        Optional<Account> optAccount = accountRepository.findById(id);

        if (optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Account account = optAccount.get();

        BeanUtils.copyProperties(dtoAccountIU,account,"id","createTime");

        Account updateAccount = accountRepository.save(account);

        BeanUtils.copyProperties(updateAccount,dtoAccount); 
        dtoAccount.setDescription(account.getAccountNo() +" numaralı bakiye güncellendi");

        return dtoAccount;
    }
}
