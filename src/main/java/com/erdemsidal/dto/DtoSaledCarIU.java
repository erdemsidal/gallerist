package com.erdemsidal.dto;

import com.erdemsidal.entity.Car;
import com.erdemsidal.entity.Customer;
import com.erdemsidal.entity.Gallerist;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCarIU {

    @NotNull
    private Long galleristId;

    @NotNull
    private Long carId;

    @NotNull
    private Long customerId;
}
