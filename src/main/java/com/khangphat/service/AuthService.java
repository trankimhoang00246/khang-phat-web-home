package com.khangphat.service;

import com.khangphat.model.dto.AuthDto;
import com.khangphat.model.form.LoginForm;
import com.khangphat.model.form.RegisterForm;

public interface AuthService {
    AuthDto login(LoginForm form);
    String register(RegisterForm form);
    AuthDto refreshJWT(String refreshToken);
}
