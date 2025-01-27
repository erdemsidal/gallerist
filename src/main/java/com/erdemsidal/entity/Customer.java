package com.erdemsidal.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "tckn")
    private String tckn;

    @Column(name = "birth_of_date")
    private Date birthOfDate;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;
}
