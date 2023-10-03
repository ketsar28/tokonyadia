package com.enigma.tokonyadia.model.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RegisterSellerRequest {
    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;
    private String password;
    private String username;
    private String storeName;
    private String mobilePhone;
}
