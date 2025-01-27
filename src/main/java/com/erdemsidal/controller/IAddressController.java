package com.erdemsidal.controller;

import com.erdemsidal.dto.DtoAddress;
import com.erdemsidal.dto.DtoAddressIU;

public interface IAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

    public RootEntity<DtoAddress> getAddressById(Long id);

    public RootEntity<DtoAddress> deleteAddressById(Long id);

    public RootEntity<DtoAddress> updateAddress(Long id,DtoAddressIU dtoAddressIU);
}
