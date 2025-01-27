package com.erdemsidal.dto;

import com.erdemsidal.enums.CarStatusType;
import com.erdemsidal.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCar extends DtoBase{


    private String plate;


    private String brand;


    private String model;


    private Integer productionYear;


    private BigDecimal price;


    private CurrencyType currencyType;


    private BigDecimal damagePrice;


    private CarStatusType carStatusType;

    private String description;

}
