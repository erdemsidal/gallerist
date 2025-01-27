package com.erdemsidal.repository;

import com.erdemsidal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    void deleteById(Optional<Customer> optCustomer);

}
