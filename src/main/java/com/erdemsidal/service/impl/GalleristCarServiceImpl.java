package com.erdemsidal.service.impl;

import com.erdemsidal.dto.*;
import com.erdemsidal.entity.Car;
import com.erdemsidal.entity.Gallerist;
import com.erdemsidal.entity.GalleristCar;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.repository.CarRepository;
import com.erdemsidal.repository.GalleristCarRepository;
import com.erdemsidal.repository.GalleristRepository;
import com.erdemsidal.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristRepository galleristRepository;



    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU){

        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());

        if (optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());

        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getCarId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());

        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;
    }



    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();

        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));


        BeanUtils.copyProperties(savedGalleristCar,dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getCar(),dtoCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(),dtoAddress);


        dtoGallerist.setAddress(dtoAddress);

        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

    @Override
    public DtoGalleristCar getGalleristCarId(Long id) {

        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoCar dtoCar = new DtoCar();
        DtoGallerist dtoGallerist = new DtoGallerist();

        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);

        if (optGalleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        GalleristCar galleristCar = optGalleristCar.get();

        Gallerist gallerist = galleristCar.getGallerist();
        Car car = galleristCar.getCar();

        BeanUtils.copyProperties(galleristCar,dtoGalleristCar);
        BeanUtils.copyProperties(gallerist,dtoGallerist);
        BeanUtils.copyProperties(car,dtoCar);

        dtoGalleristCar.setCar(dtoCar);
        dtoGalleristCar.setGallerist(dtoGallerist);


        return dtoGalleristCar;
    }

    @Override
    public DtoGalleristCar deleteGalleristCarId(Long id) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();

        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);


        if (optGalleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        GalleristCar galleristCar = optGalleristCar.get();

        Gallerist gallerist = galleristCar.getGallerist();
        Car car = galleristCar.getCar();

        BeanUtils.copyProperties(gallerist,dtoGallerist);
        BeanUtils.copyProperties(car,dtoCar);

        BeanUtils.copyProperties(galleristCar,dtoGalleristCar);

        dtoGalleristCar.setCar(dtoCar);
        dtoGalleristCar.setGallerist(dtoGallerist);

        dtoGalleristCar.setDescription(id +" numaralı kayıt silinmiştir.");

        galleristCarRepository.delete(galleristCar);

        return dtoGalleristCar;
    }

    @Override
    public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU) {

        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();

        Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);

        if (optGalleristCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }
        GalleristCar galleristCar = optGalleristCar.get();

         Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());

         if (optGallerist.isEmpty()){
             throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGalleristId().toString()));
         }

         Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());

        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristCarIU.getGalleristId().toString()));
        }

        Car car = optCar.get();
        Gallerist gallerist = optGallerist.get();

        gallerist.setAddress(optGallerist.get().getAddress());

        galleristCar.setCar(car);
        galleristCar.setGallerist(gallerist);

        BeanUtils.copyProperties(dtoGalleristCarIU,galleristCar,"id","createTime");

        GalleristCar updatedGalleristCar = galleristCarRepository.save(galleristCar);

        BeanUtils.copyProperties(updatedGalleristCar,dtoGalleristCar);

        BeanUtils.copyProperties(updatedGalleristCar.getCar(),dtoCar);

        BeanUtils.copyProperties(updatedGalleristCar.getGallerist(),dtoGallerist);

        BeanUtils.copyProperties(gallerist.getAddress(),dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        dtoGalleristCar.setCar(dtoCar);
        dtoGalleristCar.setGallerist(dtoGallerist);

        if (optGallerist.isPresent() && optCar.isPresent()) {
            dtoGalleristCar.setDescription(
                    String.format("%d numaralı gallerist ve %d numaralı araba güncellenmiştir.",
                            optGallerist.get().getId(),
                            optCar.get().getId())
            );
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Gallerist ve Car bulunamadı."));
        }


        return dtoGalleristCar;
    }
}
