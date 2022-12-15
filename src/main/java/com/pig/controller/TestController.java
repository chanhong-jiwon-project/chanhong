package com.pig.controller;

import com.pig.exception.CustomException;
import com.pig.enums.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/exception")
    public void exceptionTest(){
        throw new CustomException(ErrorCode.TEST_EXCEPTION);
    }
}
