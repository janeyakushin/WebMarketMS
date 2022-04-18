package ru.yajaneya.webmarket.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
    private String email;
}
