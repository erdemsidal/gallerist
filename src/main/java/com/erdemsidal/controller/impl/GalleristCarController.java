package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.IGalleristCarController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.DtoGalleristCar;
import com.erdemsidal.dto.DtoGalleristCarIU;
import com.erdemsidal.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class GalleristCarController  extends BaseController implements IGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoGalleristCar> saveGalleristCar(@RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }

    @Override
    @GetMapping("/get/{id}")
    public RootEntity<DtoGalleristCar> getGalleristCarId(@PathVariable("id") Long id) {
        return ok(galleristCarService.getGalleristCarId(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<DtoGalleristCar> deleteGalleristCarId(@PathVariable("id") Long id) {
        return ok(galleristCarService.deleteGalleristCarId(id));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoGalleristCar> updateGalleristCar(@PathVariable("id") Long id, @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.updateGalleristCar(id,dtoGalleristCarIU));
    }
}
