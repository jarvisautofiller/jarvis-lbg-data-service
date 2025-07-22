package com.example.jarvis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LBG_USER_DATA")
@Data
public class UserData {

    @Id
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "PROFESSION", nullable = false)
    private String profession;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "IFSC_CODE")
    private String ifscCode;

    @Column(name = "INCOME")
    private String income;
}
