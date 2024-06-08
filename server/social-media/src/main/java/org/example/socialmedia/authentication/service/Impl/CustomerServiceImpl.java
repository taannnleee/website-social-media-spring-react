package org.example.socialmedia.authentication.service.Impl;

import org.example.socialmedia.common.entities.Customer;
import org.example.socialmedia.authentication.repositories.CustomerRepository;
import org.example.socialmedia.authentication.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(Long customerDTO_id) {
        return null;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer =  customerRepository.findByEmail(email);
        return customer.orElse(null);
    }
}
