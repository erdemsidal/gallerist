package com.erdemsidal.service.impl;

import com.erdemsidal.dto.DtoAddress;
import com.erdemsidal.dto.DtoAddressIU;
import com.erdemsidal.entity.Address;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.repository.AddressRepository;
import com.erdemsidal.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;


    private Address createAddress(DtoAddressIU dtoAddressIU){
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU,address);

        return address;
    }


    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();

        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));

        BeanUtils.copyProperties(savedAddress,dtoAddress);

        return dtoAddress ;
    }

    @Override
    public DtoAddress getAddressById(Long id) {

        DtoAddress dtoAddress = new DtoAddress();

        Optional<Address> optAddress = addressRepository.findById(id);

        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Address address = optAddress.get();

        BeanUtils.copyProperties(address,dtoAddress);

        return dtoAddress;
    }

    @Override
    public DtoAddress deleteAddressById(Long id) {

        DtoAddress dtoAddress = new DtoAddress();

        Optional<Address> optAddress =  addressRepository.findById(id);

        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }
        Address address = optAddress.get();

        BeanUtils.copyProperties(address,dtoAddress);

        dtoAddress.setDescription(id+" numaralı adres silinmiştir.");

        addressRepository.delete(address);

        return dtoAddress;
    }

    @Override
    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {

        DtoAddress dtoAddress = new DtoAddress();

        Optional<Address> optAddress = addressRepository.findById(id);

        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Address address = optAddress.get();

        BeanUtils.copyProperties(dtoAddressIU,address,"id","createTime");

        Address updateAddress = addressRepository.save(address);

        BeanUtils.copyProperties(updateAddress,dtoAddress);
        dtoAddress.setDescription(id +" numaralı address güncellendi.");

        return dtoAddress;
    }
}
