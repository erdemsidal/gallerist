package com.erdemsidal.service;

import com.erdemsidal.dto.DtoSaledCar;
import com.erdemsidal.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
