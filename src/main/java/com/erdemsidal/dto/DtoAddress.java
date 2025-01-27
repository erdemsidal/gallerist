package com.erdemsidal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class DtoAddress extends DtoBase{

    private String city;

    private String district;

    private String neighborhood;

    private String street;

    private String description;

}
