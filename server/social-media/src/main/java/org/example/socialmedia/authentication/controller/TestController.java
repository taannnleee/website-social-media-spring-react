package org.example.socialmedia.authentication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.socialmedia.authentication.dto.CustomerDTO;
import org.example.socialmedia.authentication.dto.reponse.ResponseData;
import org.example.socialmedia.authentication.dto.reponse.ResponseSuccess;
import org.example.socialmedia.authentication.repositories.CustomerRepository;
import org.example.socialmedia.authentication.service.CustomerService;
import org.example.socialmedia.common.entities.Customer;
import org.example.socialmedia.sendEmail.service.AccountService;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
@Tag(name = "Test Controller")
public class TestController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private static final Logger log = LogManager.getLogger(AuthenticationController.class);


    @PostMapping("/test")
    public ResponseEntity<?> checkRegister(@Valid @RequestBody CustomerDTO customerDTO,
                                           @RequestParam("city") String city,
                                           @RequestParam("district") String district,
                                           @RequestParam("street") String street) {
        try {
            // Trả về một đối tượng JSON chứa kết quả của quá trình đăng ký
            log.info("success");
            return ResponseEntity.ok().body("Registration successful!");
        } catch (Exception e) {
            // Xử lý lỗi và trả về một đối tượng JSON chứa thông báo lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }

    @PutMapping("/test/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestParam(required = false) boolean status){
        try{
            return ResponseEntity.status(HttpStatus.OK).body("Update Success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update Error");
        }
    }

    @DeleteMapping("test/{id}")
    public ResponseSuccess delete(@PathVariable int id){
        return new ResponseSuccess(HttpStatus.CREATED, "Success", 1);
    }


    @Operation(summary = "Add user", description = "API create new user")
    @GetMapping("test")
    public ResponseData<Integer> get(){
        return new ResponseData<>(HttpStatus.CREATED.value(), "Success", 1);
    }

    @PostMapping("/save")
    public void saveCustomer(){
        Customer customer   = new Customer();
        customerRepository.save(customer);
    }

    @PostMapping("/getAll")
    public List<Customer> getAll(){
        return  customerRepository.findAll();
    }
}