package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getActiveUsers() {

        List<User> users = userRepository.findByStatus("ACTIVE");

        return users.stream()
                .filter(user -> user.getEmail().contains("@"))
                .map(this::normalizeUser)
                .collect(Collectors.toList());
    }

    private User normalizeUser(User user) {

        // Hidden runtime problem:
        // getEmail() may return null
        String email = user.getEmail().trim().toLowerCase();

        // Business logic problem:
        // This changes the entity before it is returned.
        user.setEmail(email);

        // Possible NPE if profile is not initialized
        user.getProfile().setVerified(true);

        return user;
    }

    @Transactional
    public User updateUser(Long id, String email) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(email);

        // Incorrect status transition
        if (email != null && email.endsWith("@company.com")) {
            user.setStatus("INACTIVE");
        }

        return userRepository.save(user);
    }

    public User getUser(Long id) {

        return userRepository.findById(id)
                .orElse(null);
    }
}