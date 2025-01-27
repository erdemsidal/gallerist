package com.erdemsidal.repository;

import com.erdemsidal.entity.GalleristCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar,Long> {
}
