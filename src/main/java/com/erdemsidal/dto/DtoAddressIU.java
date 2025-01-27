package com.erdemsidal.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DtoAddressIU {


    @NotEmpty
    private String city;

    @NotEmpty
    private String district;

    @NotEmpty
    private String neighborhood;

    @NotEmpty
    private String street;

}
