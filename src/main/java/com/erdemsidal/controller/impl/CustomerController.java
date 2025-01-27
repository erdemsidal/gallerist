package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.ICustomerController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.DtoCustomer;
import com.erdemsidal.dto.DtoCustomerIU;
import com.erdemsidal.entity.Customer;
import com.erdemsidal.service.ICustomerService;
import com.erdemsidal.utils.PageableEntity;
import com.erdemsidal.utils.PageableRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController extends BaseController implements ICustomerController {

    @Autowired
    private ICustomerService customerService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.saveCustomer(dtoCustomerIU));
    }

    @Override
    @GetMapping("/get/{id}")
    public RootEntity<DtoCustomer> getCustomerById(@PathVariable("id") Long id) {
        return ok(customerService.getCustomerById(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<DtoCustomer> deleteCustomerById(@PathVariable("id")Long id) {
        return ok(customerService.deleteCustomerById(id));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoCustomer> updateCustomer(@PathVariable("id")Long id, @RequestBody DtoCustomerIU dtoCustomerIU) {
        return ok(customerService.updateCustomer(id,dtoCustomerIU));
    }

    @Override
    @GetMapping("/getAll/pageable")
    public RootEntity<PageableEntity<DtoCustomer>> findAllPageable(PageableRequest request) {
        Page<Customer> page = customerService.findAllPageable(toPageable(request));
        PageableEntity<DtoCustomer> pageableResponse = toPageableResponse(page, customerService.toDTOList(page.getContent()));
        return ok(pageableResponse);
    }
}














