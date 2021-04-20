package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.SessionUser;
import com.example.demo.domain.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public User getCurrentUser(UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    public User getLoginUser(SessionUser user) {
        return userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", user.getEmail()));
    }
}
