package com.ultravision.rentalsys.repository;


import com.ultravision.rentalsys.model.Customer.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {


    Customer findByCustomerId(Long customerId);
    List<Customer> findByNameContaining(String name);
    List<Customer> findAll();
    Customer save(Customer customer);
    //List<Customer> countCustomer();

}
