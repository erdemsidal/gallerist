package com.erdemsidal.service;


import com.erdemsidal.dto.DtoAddress;
import com.erdemsidal.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

    public DtoAddress getAddressById(Long id);

    public DtoAddress deleteAddressById(Long id);

    public DtoAddress updateAddress(Long id,DtoAddressIU dtoAddressIU);

}
