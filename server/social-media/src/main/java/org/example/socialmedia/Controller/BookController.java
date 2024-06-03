package org.example.socialmedia.Controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class BookController {

    @GetMapping("/books")
    public String getAllBooks() {
        // Đây là nơi thực hiện logic để lấy tất cả sách từ cơ sở dữ liệu hoặc một nguồn dữ liệu khác
        // Ở đây, chúng ta chỉ trả về một chuỗi làm ví dụ
        return "Danh sách tất cả các sách";
    }

}