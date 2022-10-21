package com.pig.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TEST_EXCEPTION(404, "테스트 예외 입니다. ");

    private final Integer status;
    private final String message;
}
