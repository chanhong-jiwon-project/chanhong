package com.pig.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequestDto {
    String userId;
    String pw;
    String name;
}
