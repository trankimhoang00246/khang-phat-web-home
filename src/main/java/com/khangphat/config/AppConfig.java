package com.khangphat.config;

import com.khangphat.model.entity.Role;
import com.khangphat.model.entity.User;
import com.khangphat.repository.RoleRepository;
import com.khangphat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public void accountConfig() {
        if(!roleRepository.existsRoleByName("ROLE_USER") && !roleRepository.existsRoleByName("ROLE_ADMIN")) {
            var userRole = Role.builder()
                    .id(1l)
                    .name("ROLE_USER")
                    .build();
            var adminRole = Role.builder()
                    .id(2l)
                    .name("ROLE_ADMIN")
                    .build();
            roleRepository.save(userRole);
            roleRepository.save(adminRole);
        }

        if(!userRepository.existsUserByUsername("admin")) {
            var admin = User.builder()
                    .id(1l)
                    .name("Admin")
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .email("admin@gmail.com")
                    .role(roleRepository.findByName("ROLE_ADMIN").get())
                    .build();
            userRepository.save(admin);
        }

        if(!userRepository.existsUserByUsername("user")) {
            var user = User.builder()
                    .id(2l)
                    .name("User")
                    .username("user")
                    .password(passwordEncoder.encode("user"))
                    .email("user@gmail.com")
                    .role(roleRepository.findByName("ROLE_USER").get())
                    .build();
            userRepository.save(user);
        }
    }
}
