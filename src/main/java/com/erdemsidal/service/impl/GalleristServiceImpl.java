package com.erdemsidal.service.impl;

import com.erdemsidal.dto.DtoAddress;
import com.erdemsidal.dto.DtoCustomerIU;
import com.erdemsidal.dto.DtoGallerist;
import com.erdemsidal.dto.DtoGalleristIU;
import com.erdemsidal.entity.Address;
import com.erdemsidal.entity.Gallerist;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.repository.AddressRepository;
import com.erdemsidal.repository.GalleristRepository;
import com.erdemsidal.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GalleristRepository galleristRepository;



    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU){


        Optional<Address> optAddress =  addressRepository.findById(dtoGalleristIU.getAddressId());

        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoGalleristIU,gallerist);

        gallerist.setAddress(optAddress.get());

        return gallerist;
    }




    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();


        Gallerist savedGallerist =  galleristRepository.save(createGallerist(dtoGalleristIU));


        BeanUtils.copyProperties(savedGallerist,dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(),dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }

    @Override
    public DtoGallerist getGalleristById(Long id) {

        DtoGallerist dtoGallerist = new DtoGallerist();

        DtoAddress dtoAddress = new DtoAddress();

        Optional<Gallerist> optGallerist = galleristRepository.findById(id);

        if (optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Gallerist gallerist = optGallerist.get();

        Address address = gallerist.getAddress();

        BeanUtils.copyProperties(gallerist,dtoGallerist);
        BeanUtils.copyProperties(address,dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }

    @Override
    public DtoGallerist deleteGalleristById(Long id) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Optional<Gallerist> optGallerist = galleristRepository.findById(id);

        if (optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Gallerist gallerist = optGallerist.get();

        Address address = gallerist.getAddress();
        BeanUtils.copyProperties(address,dtoAddress);

        BeanUtils.copyProperties(gallerist,dtoGallerist);

        dtoGallerist.setAddress(dtoAddress);
        dtoGallerist.setDescription(id +" numaralı gallerist silinmiştir.");

        galleristRepository.delete(gallerist);


        return dtoGallerist;
    }

    @Override
    public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Optional<Gallerist> optGallerist = galleristRepository.findById(id);

        if (optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Gallerist gallerist = optGallerist.get();

        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());

        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristIU.getAddressId().toString()));
        }

        Address address = optAddress.get();

        BeanUtils.copyProperties(dtoGalleristIU,gallerist,"id","createTime");

        gallerist.setAddress(address);

        Gallerist updatedGallerist = galleristRepository.save(gallerist);

        BeanUtils.copyProperties(updatedGallerist,dtoGallerist);

        BeanUtils.copyProperties(updatedGallerist.getAddress(),dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        dtoGallerist.setDescription(id +" numaralı gallerist güncellenmiştir.");
        return dtoGallerist;
    }
}
