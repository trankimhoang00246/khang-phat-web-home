package com.khangphat.service.impl;

import com.khangphat.exception.InvalidRefreshTokenException;
import com.khangphat.model.dto.AuthDto;
import com.khangphat.model.entity.Role;
import com.khangphat.model.entity.User;
import com.khangphat.model.form.LoginForm;
import com.khangphat.model.form.RegisterForm;
import com.khangphat.repository.RoleRepository;
import com.khangphat.repository.UserRepository;
import com.khangphat.security.JwtService;
import com.khangphat.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthDto login(LoginForm form) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword())
        );
        String accessToken = jwtService.generateAccessToken(authentication);
        String refreshToken = jwtService.generateRefreshToken(authentication);

        return AuthDto.from(authentication, accessToken, refreshToken);
    }

    @Override
    public String register(RegisterForm form) {
        if (!roleRepository.existsRoleByName("ROLE_USER")) {
            var role = Role.builder()
                    .name("ROLE_USER")
                    .build();
            roleRepository.save(role);
        }

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("Not found ROLE_USER"));

        var user = User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .role(role)
                .build();

        userRepository.save(user);
        return "Success register new user";
    }

    @Override
    public AuthDto refreshJWT(String refreshToken) {
        if( refreshToken != null ){
            refreshToken = refreshToken.replaceFirst("Bearer ", "");
            if(jwtService.validateRefreshToken(refreshToken) ){
                Authentication auth = jwtService.createAuthentication(refreshToken);
                return AuthDto.from(auth, jwtService.generateAccessToken(auth), refreshToken);
            }
        }
        throw new InvalidRefreshTokenException(refreshToken);
    }
}
