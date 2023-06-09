package com.example.demotestback.web;

import com.example.demotestback.entities.User;
import com.example.demotestback.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
public class UserRestController {

    private UserService userService;

    @GetMapping("/user")
    public List<User> getUser(){
        return userService.allUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user){
        User users = userService.saveUser(user);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@RequestBody SignInRequest signInRequest) {
        try {
            User user = userService.signIn(signInRequest.getUsername(), signInRequest.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
