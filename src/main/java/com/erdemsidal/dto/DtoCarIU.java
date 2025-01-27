package com.erdemsidal.dto;

import com.erdemsidal.enums.CarStatusType;
import com.erdemsidal.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCarIU {

    @NotNull
    private String plate;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Integer productionYear;

    @NotNull
    private BigDecimal price;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;
}
