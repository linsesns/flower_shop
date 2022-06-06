package com.example.flowershop.service;

import com.example.flowershop.entity.Flower;
import com.example.flowershop.entity.User;
import com.example.flowershop.enums.Role;
import com.example.flowershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User getUserByPrincipalName(String name) {
        return userRepository.findByEmail(name);
    }

    public void buyFlower(User user, Flower flower) {
        user.getFlowers().add(flower);
        userRepository.save(user);
    }

    public Set<Flower> getUserFlowers(String name) {
        return userRepository.findByEmail(name).getFlowers();
    }
}
