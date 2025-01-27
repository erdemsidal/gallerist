package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.IAddressController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.DtoAddress;
import com.erdemsidal.dto.DtoAddressIU;
import com.erdemsidal.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/address")
public class AddressController extends BaseController implements IAddressController {

    @Autowired
    private IAddressService addressService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }

    @Override
    @GetMapping("/get/{id}")
    public RootEntity<DtoAddress> getAddressById(@PathVariable("id") Long id) {
        return ok(addressService.getAddressById(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<DtoAddress> deleteAddressById(@PathVariable("id") Long id) {
        return ok(addressService.deleteAddressById(id));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoAddress> updateAddress(@PathVariable("id") Long id, @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.updateAddress(id,dtoAddressIU));
    }
}
