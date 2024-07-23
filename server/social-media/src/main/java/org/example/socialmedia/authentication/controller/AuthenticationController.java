package org.example.socialmedia.authentication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.common.entities.Customer;
import org.example.socialmedia.authentication.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthenticationController {
    private final CustomerService customerService;
    @GetMapping("index")
    public ResponseData<Customer> index(){
        Customer customer =  customerService.getCustomerByEmail("leetaan1902@gmail.com");
        return new ResponseData<>(HttpStatus.CREATED.value(), "Success", customer);
    }

}

