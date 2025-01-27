package com.erdemsidal.controller;


import com.erdemsidal.dto.DtoCustomer;
import com.erdemsidal.dto.DtoCustomerIU;
import com.erdemsidal.entity.Customer;
import com.erdemsidal.utils.PageableEntity;
import com.erdemsidal.utils.PageableRequest;
import org.springframework.data.domain.Page;

public interface ICustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

    public RootEntity<DtoCustomer> getCustomerById(Long id);

    public RootEntity<DtoCustomer> deleteCustomerById(Long id);

    public RootEntity<DtoCustomer> updateCustomer(Long id, DtoCustomerIU dtoCustomerIU);

    public RootEntity<PageableEntity<DtoCustomer>> findAllPageable(PageableRequest request);
}
