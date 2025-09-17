package com.joaopenascimento.rest_with_spring_boot.service;

import com.joaopenascimento.rest_with_spring_boot.model.User;
import com.joaopenascimento.rest_with_spring_boot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public User register(String username, String rawPassword, String role) {
        System.out.println("Senha recebida: " + rawPassword);
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);

        return userRepository.save(user);
    }

}
