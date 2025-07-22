package com.example.jarvis.service;

import com.example.jarvis.entity.UserData;


import java.util.Optional;

public interface UserService {
    Optional<UserData> getDataByPhoneNumber(String phoneNumber);
    Object getUserDataByPhoneNumber(String phoneNumber);
}
