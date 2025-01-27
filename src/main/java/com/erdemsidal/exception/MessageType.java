package com.erdemsidal.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004","Kayıt Bulunamadı."),
    USERNAME_NOT_FOUND("1006","username bulunamadı"),
    TOKEN_IS_EXPIRED("1005","Token'ın süresi bitmiştir"),
    GENERAL_EXCEPTION("9999","Genel bir hata oluştu"),
    CURRENCY_RATES_IS_OCCURED("1002","Döviz kuru Alınamadı."),
    CUSTOMER_INSUFFICIENT_BALANCE("1010","Müşterinin Bakiyesi Yetersiz"),
    CAR_STATUS_IS_ALDREADY_SALED("1012","Araba satılmış gözüküyor"),
    USERNAME_OR_PASSWORD_INVALID("1007","kullanıcı adı veya şifre bulunamadı."),
    REFRESH_TOKEN_NOT_FOUND("1008","Refresh Token bulunamadı."),
    REFRESH_TOKEN_IS_EXPIRED("1009","Refresh Token süresi dolmuştur.");


    private String code;

    private String message;


     MessageType(String code,String message){
        this.code = code;
        this.message = message;
    }
}
