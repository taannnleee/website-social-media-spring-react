package org.example.socialmedia.authentication.service;


import org.example.socialmedia.common.entities.Customer;

public interface CustomerService {
    Customer getCustomerById(Long customerDTO_id);
    Customer getCustomerByEmail(String email);
}
