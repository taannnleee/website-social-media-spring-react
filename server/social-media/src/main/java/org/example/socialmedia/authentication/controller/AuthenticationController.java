package org.example.socialmedia.authentication.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.socialmedia.authentication.dto.CustomerDTO;
import org.example.socialmedia.authentication.entities.Address;
import org.example.socialmedia.authentication.entities.Customer;
import org.example.socialmedia.authentication.service.CustomerService;
import org.example.socialmedia.common.Enum.EMessage;
import org.example.socialmedia.sendEmail.service.AccountService;
import org.example.socialmedia.sendEmail.service.EmailService;
import org.example.socialmedia.sendEmail.utils.OTPGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.example.socialmedia.common.Enum.EMessage.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {
    private final CustomerService customerService;
    private final EmailService emailService;
    private final AccountService accountService;
    @GetMapping("/login")
    public String Login(Model model) {
        try {
            return "views/login";
        } catch (Exception e) {

            model.addAttribute("error", "Lỗi đăng nhập: " + e.getMessage());
            return "views/error";
        }
    }

    @GetMapping("/register")
    public String Register(Model model) {
        try {
            return "views/register";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi đăng kí: " + e.getMessage());
            return "views/error";
        }
    }

    @PostMapping("/register")
    public String checkRegister(@ModelAttribute CustomerDTO customerDTO, Model model, @RequestParam("city")String city, @RequestParam("district")String district, @RequestParam("stress")String stress) {
        return "hihi";
    }

    @GetMapping("/logout")
    private String logout(HttpSession session){
        session.setAttribute("customer", null);
        session.setAttribute("cart", null);
        session.setAttribute("isAdmin", null);
        System.out.println(session.getAttribute("customer"));
        return "redirect:/index";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model){
        try{
            return "views/passwordResult";
        } catch(Exception e){
            model.addAttribute("error", "Lỗi đổi mật khẩu: " + e.getMessage());
            return "views/error";
        }
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword, Model model) {
//        EMessage result = customerService.changePassword(email, oldPassword, newPassword);
//        model.addAttribute("result", result.getValue());
        return "views/passwordResult";
    }

    @GetMapping("/forgot-pass")
    public String startForgotPass(){
        try{
            return "views/your-email";
        }catch (Exception e){
            return "views/error";
        }
    }

    @PostMapping("/forgot-pass-result")
    public String changePassword(@RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 Model model) {
        String url = "views/login";
        if(newPassword.equals(confirmPassword)){
            String email = (String) session.getAttribute("email");
//            EMessage result = customerService.changePassword(email, newPassword);
//            model.addAttribute("result", result.getValue());
        }else{
            url = "views/forgot-pass";
            model.addAttribute("result", CONFIRM_PASSWORD_NOT_MATCH.getValue());
        }
        return url;
    }

    @GetMapping("/otp")
    public String sendOTP(@RequestParam String email,
                          HttpSession session,
                          Model model){
        try{
            Customer customer = customerService.getCustomerByEmail(email);
            if(customer == null){
                model.addAttribute("result",EMessage.CUSTOMER_NOT_EXIST.getValue());
                return "views/your-email";
            }else{
                String OTP = OTPGenerator.generateOTP();
                String OTPHash = accountService.hashPassword(OTP);
                emailService.sendEmail(email,EMessage.TITLE_OTP.getValue(),EMessage.TEXT_EMAIL_OTP.getValue()+OTP);
                session.setAttribute("OTPHash",OTPHash);
                session.setAttribute("email",email);
                return "views/type-otp";
            }
        }catch (Exception e){
            return "views/error";
        }
    }

    @PostMapping("/otp")
    public String checkOTP(@RequestParam String otp,
                           HttpSession session,
                           Model model){
        try{
            String url="views/forgot-pass";
            String OTPHash = (String) session.getAttribute("OTPHash");
            if(OTPHash == null)
                OTPHash = url;
            if(accountService.hashPassword(otp).equals(OTPHash)){
                session.setAttribute("OTPHash",null);
            }
            else{
                model.addAttribute("result",EMessage.OTP_NOT_MATCH.getValue());
                url = "views/type-otp";
            }
            return url;
        }catch (Exception e){
            return "views/error";
        }
    }

}

