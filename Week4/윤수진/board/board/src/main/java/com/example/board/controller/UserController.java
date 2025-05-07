package com.example.board.controller;

import com.example.board.dto.SignupRequest;
import com.example.board.dto.LoginRequest;
import com.example.board.dto.LoginResponse;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest request) {
        userService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        String result = userService.login(request);
        return new LoginResponse(result);
    }
}
