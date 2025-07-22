package com.example.jarvis.controller;

import com.example.jarvis.model.IdRequest;
import com.example.jarvis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserDataController {

    private final UserService userService;

    @Autowired
    public UserDataController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/lbgUserDetails")
    public ResponseEntity<?> getUserDataByPhoneNumber(@RequestBody IdRequest request) {
        try {
            Object result = userService.getUserDataByPhoneNumber(request.getPhoneNumber());
             System.out.println(result);
            if (result == null || result.toString().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No user with that phone number:"+request.getPhoneNumber());
            }
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

