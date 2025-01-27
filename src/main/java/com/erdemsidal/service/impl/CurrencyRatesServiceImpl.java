package com.erdemsidal.service.impl;

import com.erdemsidal.dto.CurrencyRatesResponse;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl  implements ICurrencyRatesService {


    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {

        String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "series=TP.DK.USD.A";
        String type = "json";

        String endPoint = rootURL + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;


        //https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A&startDate=16-12-2024&endDate=16-12-2024&type=json

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "JpIPWe1gaW");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);


        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED,e.getMessage()));
        }
        return null;

    }
}

