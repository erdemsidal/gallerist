package com.erdemsidal.service;

import com.erdemsidal.dto.DtoCustomer;
import com.erdemsidal.dto.DtoCustomerIU;
import com.erdemsidal.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

    public DtoCustomer getCustomerById(Long id);

    public DtoCustomer deleteCustomerById(Long id);

    public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU);

    public Page<Customer> findAllPageable(Pageable pageable);

    public List<DtoCustomer> toDTOList(List<Customer> customerList);
}
