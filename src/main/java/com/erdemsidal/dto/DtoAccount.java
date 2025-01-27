package com.erdemsidal.dto;

import com.erdemsidal.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoAccount extends DtoBase {


    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;

    private String description;
}
