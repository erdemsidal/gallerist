package com.erdemsidal.service;

import com.erdemsidal.dto.DtoGallerist;
import com.erdemsidal.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

    public DtoGallerist getGalleristById(Long id);

    public DtoGallerist deleteGalleristById(Long id);

    public DtoGallerist updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);

}
