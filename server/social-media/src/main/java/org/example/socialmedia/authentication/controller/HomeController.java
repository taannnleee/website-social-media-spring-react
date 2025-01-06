package org.example.socialmedia.authentication.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socialmedia.authentication.dto.response.ProfileResponse;
import org.example.socialmedia.authentication.dto.response.ResponseData;
import org.example.socialmedia.authentication.exception.UserNotFoundException;
import org.example.socialmedia.authentication.repositories.UserRepository;
import org.example.socialmedia.authentication.service.Impl.AuthencationService;
import org.example.socialmedia.authentication.service.ProductService;
import org.example.socialmedia.authentication.service.UserService;
import org.example.socialmedia.common.entities.Product;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
@Slf4j
public class HomeController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthencationService authencationService;
    private final ProductService productService;

    @GetMapping("/getAllProduct")
    public ResponseData<?> getAllProduct(){
        try{
            System.out.println("hihi");
            System.out.println("hiih6i");
            System.out.println("hihihi");
            List<Product> productList =  productService.getAllProduct();

            return new ResponseData<>(HttpStatus.OK.value(), "get all product successfully",productList);

        }catch (UserNotFoundException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
