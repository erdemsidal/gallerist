package com.erdemsidal.controller;

import com.erdemsidal.dto.DtoCar;
import com.erdemsidal.dto.DtoCarIU;

public interface ICarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

    public RootEntity<DtoCar> getCarById(Long id);

    public RootEntity<DtoCar> deleteCarById(Long id);

    public RootEntity<DtoCar> updateCar(Long id ,DtoCarIU dtoCarIU);

}
