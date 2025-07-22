package com.example.jarvis.service;

import com.example.jarvis.entity.UserData;


import com.example.jarvis.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
  
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




@Override
    public Optional<UserData> getDataByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(userRepository.findByPhoneNumber(phoneNumber));
    }

    @Override
    public Object getUserDataByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

            Optional<UserData> userData = getDataByPhoneNumber(phoneNumber);
        if (userData == null) {
            return null; // No user found with the given phone number
        }

        // Return the user data as needed, e.g., as a DTO or directly
        return userData;
    }
}
