package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.ISaledCarController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.DtoSaledCar;
import com.erdemsidal.dto.DtoSaledCarIU;
import com.erdemsidal.repository.SaledCarRepository;
import com.erdemsidal.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/saled-car")
public class SaledCarController extends BaseController implements ISaledCarController {

    @Autowired
    private ISaledCarService saledCarService;


    @Override
    @PostMapping("/save")
    public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
        return ok(saledCarService.buyCar(dtoSaledCarIU));
    }
}
