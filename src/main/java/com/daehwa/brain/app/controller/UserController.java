package com.daehwa.brain.app.controller;

import com.daehwa.brain.app.mapper.UserMapper;
import com.daehwa.brain.app.model.UserDto;
import com.daehwa.brain.app.service.UserService;
import com.daehwa.brain.app.vo.RequestUser;
import com.daehwa.brain.app.vo.ResponseUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }


    @GetMapping("/health_check")

    public String status() {
        return "OK on Port";
    }

    @PostMapping
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser request) {
        UserDto user = userMapper.requestToDto(request);

        UserDto savedUser = userService.createUser(user);

        ResponseUser response = userMapper.dtoToResponse(savedUser);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }


}
