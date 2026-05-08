package com.example.l5_20233564.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.l5_20233564.entity.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}