package com.erdemsidal.dto;

import com.erdemsidal.entity.Car;
import com.erdemsidal.entity.Gallerist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoGalleristCar  extends DtoBase{


    private DtoGallerist gallerist;


    private DtoCar car;

    private String description;
}
