package com.erdemsidal.controller;

import com.erdemsidal.dto.DtoGallerist;
import com.erdemsidal.dto.DtoGalleristIU;
import jakarta.persistence.criteria.Root;

public interface IGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

    public RootEntity<DtoGallerist> getGalleristById(Long id);

    public RootEntity<DtoGallerist> deleteGalleristById(Long id);

    public RootEntity<DtoGallerist> updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);



}
