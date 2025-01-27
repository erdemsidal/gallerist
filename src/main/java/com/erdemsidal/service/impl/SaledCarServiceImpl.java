package com.erdemsidal.service.impl;

import com.erdemsidal.dto.*;
import com.erdemsidal.entity.Car;
import com.erdemsidal.entity.Customer;
import com.erdemsidal.entity.SaledCar;
import com.erdemsidal.enums.CarStatusType;
import com.erdemsidal.exception.BaseException;
import com.erdemsidal.exception.ErrorMessage;
import com.erdemsidal.exception.MessageType;
import com.erdemsidal.repository.CarRepository;
import com.erdemsidal.repository.CustomerRepository;
import com.erdemsidal.repository.GalleristRepository;
import com.erdemsidal.repository.SaledCarRepository;
import com.erdemsidal.service.ICurrencyRatesService;
import com.erdemsidal.service.ISaledCarService;
import com.erdemsidal.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {


    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;



    public BigDecimal convertCustomerAmountToUSD(Customer customer){
        CurrencyRatesResponse currencyRates =
                currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));

        BigDecimal usd =  new BigDecimal(currencyRates.getItems().get(0).getUsd());


        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd,2, RoundingMode.HALF_UP);

        return customerUSDAmount; // divide bölmek için kullanır amountu yani tutarı usd ye böler.

    }


    //35000 dolar var diyelim adamın parasını dolardan tl olarak çevirip kaydetmemiz lazım.
    public BigDecimal remainingCustomerAmount(Customer customer,Car car){

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);

        BigDecimal remainingUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse =
                currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));

        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal remainingTlAmount = remainingUSDAmount.multiply(usd).setScale(2,RoundingMode.HALF_UP);

        return remainingTlAmount;

    }


    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU){

        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());

        if (optCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCustomerId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());

        if (optCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCarId().toString()));
        }

        BigDecimal customerAmountToUSD = convertCustomerAmountToUSD(optCustomer.get());

        if (customerAmountToUSD.compareTo(optCar.get().getPrice()) ==0 ||
                customerAmountToUSD.compareTo(optCar.get().getPrice())>0){
            return true;
        }
        return false;
    }


    public boolean checkCarStatus(Long carId){

        Optional<Car> optCar =  carRepository.findById(carId);

        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())){
            return false;
        }
        return true;
    }




    public SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU){
        SaledCar saledCar = new SaledCar();

        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if (!checkCarStatus(dtoSaledCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALDREADY_SALED,dtoSaledCarIU.getCarId().toString()));
        }
        if (!checkAmount(dtoSaledCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_INSUFFICIENT_BALANCE,""));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);


        carRepository.save(car);

        Customer customer = new Customer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer,car));
        customerRepository.save(customer);

        return toDTO(savedSaledCar);
    }

    public DtoSaledCar toDTO(SaledCar saledCar){
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoCar dtoCar = new DtoCar();
        DtoGallerist dtoGallerist = new DtoGallerist();


        BeanUtils.copyProperties(saledCar,dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(),dtoCustomer);
        BeanUtils.copyProperties(saledCar.getCar(),dtoCar);
        BeanUtils.copyProperties(saledCar.getGallerist(),dtoGallerist);

        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCar(dtoCar);
        dtoSaledCar.setCustomer(dtoCustomer);

        return dtoSaledCar;
    }



}
