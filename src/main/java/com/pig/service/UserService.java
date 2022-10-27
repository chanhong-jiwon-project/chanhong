package com.pig.service;

import com.pig.domain.User;
import com.pig.dto.JwtResponseDto;
import com.pig.dto.SignInRequestDto;
import com.pig.dto.SignUpRequestDto;
import com.pig.exception.CustomException;
import com.pig.exception.enums.ErrorCode;
import com.pig.repository.UserRepository;
import com.pig.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<?> loginUser(SignInRequestDto signInRequestDto) throws Exception {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequestDto.getUserId());
        authenticate(signInRequestDto.getUserId(), signInRequestDto.getPw());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    public ResponseEntity<?> registerUser(SignUpRequestDto signUpRequestDto) {
        Optional<User> found = userRepository.findById(signUpRequestDto.getUserId());
        if (found.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_USER);
        }
        userRepository.save(User.builder()
                .userId(signUpRequestDto.getUserId())
                .name(signUpRequestDto.getName())
                .password(passwordEncoder.encode(signUpRequestDto.getPw()))
                .build());


        return ResponseEntity.ok("회원가입 완료");
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
