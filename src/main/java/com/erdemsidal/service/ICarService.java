package com.erdemsidal.service;

import com.erdemsidal.dto.DtoCar;
import com.erdemsidal.dto.DtoCarIU;
import com.erdemsidal.entity.Car;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

    public DtoCar getCarById(Long id);

    public DtoCar deleteCarById(Long id);

    public DtoCar updateCar(Long id ,DtoCarIU dtoCarIU);
}
