package com.pig.exception;

import com.pig.exception.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
}