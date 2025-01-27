package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.ICurrencyRatesController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.CurrencyRatesResponse;
import com.erdemsidal.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/")
public class CurrencyRatesController extends BaseController implements ICurrencyRatesController  {


    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Override
    @GetMapping("/currency-rates")
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,
                                                              @RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate,endDate));
    }
}
