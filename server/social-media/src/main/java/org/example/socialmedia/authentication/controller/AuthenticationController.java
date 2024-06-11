package org.example.socialmedia.authentication.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.socialmedia.authentication.dto.CustomerDTO;
import org.example.socialmedia.authentication.dto.reponse.ResponseData;
import org.example.socialmedia.authentication.dto.request.RegistrationRequest;
import org.example.socialmedia.common.entities.Customer;
import org.example.socialmedia.authentication.service.CustomerService;
import org.example.socialmedia.common.Enum.EMessage;
import org.example.socialmedia.sendEmail.service.AccountService;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.example.socialmedia.sendEmail.utils.OTPGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.example.socialmedia.common.Enum.EMessage.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {
    private static final Logger log = LogManager.getLogger(AuthenticationController.class);
    private final CustomerService customerService;
    @GetMapping("index")
    public ResponseData<Customer> index(){
        Customer customer =  customerService.getCustomerByEmail("leetaan1902@gmail.com");
        return new ResponseData<>(HttpStatus.CREATED.value(), "Success", customer);
    }
//    @PostMapping("/register")
//    public void register(RegistrationRequest request){
//
//    }

}

