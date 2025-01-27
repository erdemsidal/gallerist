package com.erdemsidal.service.impl;

import com.erdemsidal.dto.DtoAccount;
import com.erdemsidal.dto.DtoAddress;
import com.erdemsidal.dto.DtoCustomer;
import com.erdemsidal.dto.DtoCustomerIU;
import com.erdemsidal.entity.Account;
import com.erdemsidal.entity.Address;
import com.erdemsidal.entity.Customer;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.repository.AccountRepository;
import com.erdemsidal.repository.AddressRepository;
import com.erdemsidal.repository.CustomerRepository;
import com.erdemsidal.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());

        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()));
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());

        if (optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()));
        }


        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU,customer);

        customer.setAccount(optAccount.get());
        customer.setAddress(optAddress.get());

        return customer;
    }


    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAccount dtoAccount = new DtoAccount();
        DtoAddress dtoAddress = new DtoAddress();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer,dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);


        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);

        return dtoCustomer;
    }

    @Override
    public DtoCustomer getCustomerById(Long id) {

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Optional<Customer> optCustomer = customerRepository.findById(id);

        if (optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Customer customer = optCustomer.get();
        Address address = customer.getAddress();
        Account account = customer.getAccount();

        BeanUtils.copyProperties(customer,dtoCustomer);
        BeanUtils.copyProperties(address,dtoAddress);
        BeanUtils.copyProperties(account,dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }

    @Override
    public DtoCustomer deleteCustomerById(Long id) {

        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();


        Optional<Customer> optCustomer = customerRepository.findById(id);

        if (optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Customer customer = optCustomer.get();

        Address address = customer.getAddress();
        Account account = customer.getAccount();

        BeanUtils.copyProperties(customer,dtoCustomer);
        BeanUtils.copyProperties(address,dtoAddress);
        BeanUtils.copyProperties(account,dtoAccount);

        dtoCustomer.setAccount(dtoAccount);
        dtoCustomer.setAddress(dtoAddress);


        customerRepository.delete(customer);

        return dtoCustomer;
    }

    @Override
    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Optional<Customer> optCustomer = customerRepository.findById(id);

        if (optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Customer customer = optCustomer.get();


        //Address
        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAddressId().toString()));
        }

        //Account
        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optAccount.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoCustomerIU.getAccountId().toString()));
        }

        BeanUtils.copyProperties(dtoCustomerIU, customer, "id", "createTime");

        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());

        Customer updatedCustomer = customerRepository.save(customer);

        BeanUtils.copyProperties(updatedCustomer,dtoCustomer);
        BeanUtils.copyProperties(updatedCustomer.getAddress(),dtoAddress);
        BeanUtils.copyProperties(updatedCustomer.getAccount(),dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);


        return dtoCustomer;
    }

    @Override
    public Page<Customer> findAllPageable(Pageable pageable) {
         Page<Customer> page = customerRepository.findAll(pageable);
         if (page.isEmpty()){
             throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,page.get().toString()));
         }
         return page;

    }

    @Override
    public List<DtoCustomer> toDTOList(List<Customer> customerList) {


        List<DtoCustomer> dtoCustomerList = new ArrayList<>();

        for (Customer customer : customerList){
            DtoCustomer dtoCustomer = new DtoCustomer();
            DtoAddress dtoAddress = new DtoAddress();
            DtoAccount dtoAccount = new DtoAccount();

            BeanUtils.copyProperties(customer,dtoCustomer);
            BeanUtils.copyProperties(customer.getAddress(),dtoAddress);
            BeanUtils.copyProperties(customer.getAccount(),dtoAccount);

            dtoCustomer.setAddress(dtoAddress);
            dtoCustomer.setAccount(dtoAccount);
            dtoCustomerList.add(dtoCustomer);

        }

        return dtoCustomerList;
    }
}
