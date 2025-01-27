package com.erdemsidal.service;

import com.erdemsidal.dto.DtoGalleristCar;
import com.erdemsidal.dto.DtoGalleristCarIU;
import com.erdemsidal.dto.DtoGalleristIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIUIU);

    public DtoGalleristCar getGalleristCarId(Long id);

    public DtoGalleristCar deleteGalleristCarId(Long id);

    public DtoGalleristCar updateGalleristCar(Long id,DtoGalleristCarIU dtoGalleristCarIU);
}
