package com.pig.controller;

import com.pig.dto.SignInRequestDto;
import com.pig.dto.SignUpRequestDto;
import com.pig.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> loginUser(@RequestBody SignInRequestDto signInRequestDto) throws Exception {

        return userService.loginUser(signInRequestDto);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestDto signUpRequestDto){

        return userService.registerUser(signUpRequestDto);
    }
}
