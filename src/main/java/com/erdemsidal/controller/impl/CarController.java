package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.ICarController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.DtoCar;
import com.erdemsidal.dto.DtoCarIU;
import com.erdemsidal.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/car")
public class CarController extends BaseController implements ICarController {

    @Autowired
    private ICarService carService;


    @Override
    @PostMapping("/save")
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }

    @Override
    @GetMapping("/get/{id}")
    public RootEntity<DtoCar> getCarById(@PathVariable("id") Long id) {
        return ok(carService.getCarById(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<DtoCar> deleteCarById(@PathVariable("id") Long id) {
        return ok(carService.deleteCarById(id));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoCar> updateCar(@PathVariable("id") Long id, @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.updateCar(id,dtoCarIU));
    }
}
