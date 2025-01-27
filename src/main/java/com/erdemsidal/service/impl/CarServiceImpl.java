package com.erdemsidal.service.impl;

import com.erdemsidal.dto.DtoCar;
import com.erdemsidal.dto.DtoCarIU;
import com.erdemsidal.entity.Car;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.repository.CarRepository;
import com.erdemsidal.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {


    @Autowired
    private CarRepository carRepository;


    private Car createCar(DtoCarIU dtoCarIU){
        Car car = new Car();

        car.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCarIU,car);

        return car;
    }




    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {

        DtoCar dtoCar = new DtoCar();

        Car savedCar = carRepository.save(createCar(dtoCarIU));

        BeanUtils.copyProperties(savedCar,dtoCar);

        return dtoCar;
    }

    @Override
    public DtoCar getCarById(Long id) {

        DtoCar dtoCar = new DtoCar();

        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Car car = optCar.get();

        BeanUtils.copyProperties(car,dtoCar);
        return dtoCar;
    }

    @Override
    public DtoCar deleteCarById(Long id) {

        DtoCar dtoCar = new DtoCar();

        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Car car = optCar.get();

        BeanUtils.copyProperties(car,dtoCar);
        dtoCar.setDescription(car.getPlate() +" plakalı araç silinmiştir.");

        carRepository.delete(car);

        return dtoCar;
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {

        DtoCar dtoCar = new DtoCar();

        Optional<Car> optCar = carRepository.findById(id);

        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,""));
        }

        Car car = optCar.get();

        BeanUtils.copyProperties(dtoCarIU,car,"id","createTime");


        Car updatedCar = carRepository.save(car);

        BeanUtils.copyProperties(updatedCar,dtoCar);

        return dtoCar;
    }


}
