package com.erdemsidal.controller;

import com.erdemsidal.dto.CurrencyRatesResponse;

public interface ICurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate,String endDate);
}
