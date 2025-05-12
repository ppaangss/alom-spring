package com.example.demo;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.*;

@ReadingConverter
@RequestMapping("/api")
public class TestController {

    @PostMapping()
    public String postMethod() {
        return "Post 요청";
    }

    @GetMapping()
    public String getMethod() {
        return "Get 요청";
    }

    @PutMapping()
    public String putMethod() {
        return "Put 요청";
    }

    @DeleteMapping()
    public String deleteMethod() {
        return "Delete 요청";
    }
}
