package ru.yajaneya.webmarket.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yajaneya.webmarket.api.exeptions.AppError;
import ru.yajaneya.webmarket.auth.dto.JwtRequest;
import ru.yajaneya.webmarket.auth.dto.JwtResponse;
import ru.yajaneya.webmarket.auth.entities.User;
import ru.yajaneya.webmarket.auth.services.UserService;
import ru.yajaneya.webmarket.auth.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> authorization(@RequestBody JwtRequest authRequest) {
        return createAuthToken(authRequest, "auth");
    }

    @PostMapping("/reg")
    public ResponseEntity<?> registration(@RequestBody JwtRequest authRequest) {
        if (userService.findByUsername(authRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError
                    (
                            "REG_SERVICE_USERNAME_IS_BUSY",
                            "Username is busy"
                    ), HttpStatus.UNAUTHORIZED);
        }
        if (userService.findByEmail(authRequest.getEmail()).isPresent()) {
            return new ResponseEntity<>(new AppError
                    (
                            "REG_SERVICE_EMAIL_IS_BUSY",
                            "Email is busy"
                    ), HttpStatus.UNAUTHORIZED);
        }
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword());
        user.setEmail(authRequest.getEmail());
        userService.save(user);
        return createAuthToken(authRequest, "reg");
    }


    private ResponseEntity<?> createAuthToken(JwtRequest authRequest, String m) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError
                    (
                            "AUTH_SERVICE_INCORRECT_USERNAME_OR_PASSWORD",
                            "Incorrect username or password"
                    ), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
