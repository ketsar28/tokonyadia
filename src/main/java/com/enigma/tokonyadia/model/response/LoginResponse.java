package com.enigma.tokonyadia.model.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoginResponse {
    private String email;
    private List<String> roles;
    private String token;
}
