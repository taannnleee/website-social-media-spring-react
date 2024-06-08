package org.example.socialmedia.authentication.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.socialmedia.authentication.dto.CustomerDTO;
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
//    private static final Logger log = LogManager.getLogger(AuthenticationController.class);
//    private final CustomerService customerService;
//    private final EmailService emailService;
//    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> checkRegister(@RequestBody CustomerDTO customerDTO,
                                           @RequestParam("city") String city,
                                           @RequestParam("district") String district,
                                           @RequestParam("street") String street) {
        try {

            // Trả về một đối tượng JSON chứa kết quả của quá trình đăng ký
//            log.info("success");
            return ResponseEntity.ok().body("Registration successful!");
        } catch (Exception e) {
            // Xử lý lỗi và trả về một đối tượng JSON chứa thông báo lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }
    @PostMapping("/a")
    public String temp(@RequestBody CustomerDTO customerDTO){
        return "user";
    }

}

