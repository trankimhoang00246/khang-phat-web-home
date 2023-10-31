package com.khangphat.rest;

import com.khangphat.model.dto.AuthDto;
import com.khangphat.model.form.LoginForm;
import com.khangphat.model.form.RegisterForm;
import com.khangphat.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthResource {
    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthDto login(@RequestBody LoginForm form) {
        return authService.login(form);
    }

    @PostMapping("/register")
    public String login(@RequestBody RegisterForm form) {
        return authService.register(form);
    }

    @GetMapping("/refresh")
    public AuthDto refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return authService.refreshJWT(refreshToken);
    }
}
