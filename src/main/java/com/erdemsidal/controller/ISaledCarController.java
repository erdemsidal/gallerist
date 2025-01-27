package com.erdemsidal.controller;

import com.erdemsidal.dto.DtoSaledCar;
import com.erdemsidal.dto.DtoSaledCarIU;

public interface ISaledCarController {

    public RootEntity<DtoSaledCar>  buyCar(DtoSaledCarIU dtoSaledCarIU);


}
