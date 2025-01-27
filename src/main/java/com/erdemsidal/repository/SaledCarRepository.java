package com.erdemsidal.repository;

import com.erdemsidal.entity.SaledCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaledCarRepository extends JpaRepository<SaledCar,Long> {

}
