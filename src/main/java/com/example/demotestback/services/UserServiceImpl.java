package com.example.demotestback.services;

import com.example.demotestback.entities.User;
import com.example.demotestback.repositories.UserRepositories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepositories userRepositories;
    @Override
    public User saveUser(User user) {
        User savedUser = userRepositories.save(user);
        return savedUser;
    }

    @Override
    public List<User> allUsers() {
        return userRepositories.findAll();
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepositories.findById(userId).orElse(null);
        return user;
    }

    @Override
    public User signIn(String username, String password) throws Exception {
        User user = userRepositories.findByUsername(username)
                .orElseThrow(() -> new Exception("Nom d'utilisateur incorrect"));
        if(!user.getPassword().equals(password)){
            throw new Exception("Mot de passe incorrect");
        }
        return user;
    }
}
