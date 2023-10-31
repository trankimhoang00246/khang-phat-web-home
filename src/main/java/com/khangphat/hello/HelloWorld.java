package com.khangphat.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("api/v1/admin/hello")
    public String adminSayHello(){
        return "Admin hello";
    }

    @GetMapping("api/v1/user/hello")
    public String userSayHello(){
        return "User hello";
    }
}
