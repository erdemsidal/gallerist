package com.erdemsidal.controller.impl;

import com.erdemsidal.controller.BaseController;
import com.erdemsidal.controller.IGalleristController;
import com.erdemsidal.controller.RootEntity;
import com.erdemsidal.dto.DtoGallerist;
import com.erdemsidal.dto.DtoGalleristIU;
import com.erdemsidal.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/gallerist")
public class GalleristController extends BaseController implements IGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
    }

    @Override
    @GetMapping("/get/{id}")
    public RootEntity<DtoGallerist> getGalleristById(@PathVariable("id") Long id) {
        return ok(galleristService.getGalleristById(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public RootEntity<DtoGallerist> deleteGalleristById(@PathVariable("id") Long id) {
        return ok(galleristService.deleteGalleristById(id));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoGallerist> updateGallerist(@PathVariable("id") Long id,@RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.updateGallerist(id,dtoGalleristIU));
    }
}
