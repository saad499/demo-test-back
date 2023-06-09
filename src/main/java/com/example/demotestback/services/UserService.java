package com.example.demotestback.services;

import com.example.demotestback.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    List<User> allUsers();
    User getUser(Long userId);

    User signIn(String username, String password) throws Exception;
}
