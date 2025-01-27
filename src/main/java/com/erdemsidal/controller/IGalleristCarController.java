package com.erdemsidal.controller;

import com.erdemsidal.dto.DtoGalleristCar;
import com.erdemsidal.dto.DtoGalleristCarIU;

public interface IGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

    public RootEntity<DtoGalleristCar> getGalleristCarId(Long id);

    public RootEntity<DtoGalleristCar> deleteGalleristCarId(Long id);

    public RootEntity<DtoGalleristCar> updateGalleristCar(Long id,DtoGalleristCarIU dtoGalleristCarIU);
}
