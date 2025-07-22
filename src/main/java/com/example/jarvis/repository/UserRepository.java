package com.example.jarvis.repository;

import com.example.jarvis.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, String> {
    UserData findByPhoneNumber(String phoneNumber);
}