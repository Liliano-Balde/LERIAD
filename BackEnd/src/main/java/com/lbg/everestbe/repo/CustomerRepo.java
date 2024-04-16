package com.lbg.everestbe.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbg.everestbe.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
