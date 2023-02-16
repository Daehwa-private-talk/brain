package com.daehwa.brain.app.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private LocalDate CreatedAt;

    private String encryptedPassword;
}
