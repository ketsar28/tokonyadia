package com.enigma.tokonyadia.model.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {
    private String email;
    private String password;
    private String name;
    private String address;
    private String mobilePhone;
}
