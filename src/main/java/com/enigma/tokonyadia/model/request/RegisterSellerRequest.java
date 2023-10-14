package com.enigma.tokonyadia.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterSellerRequest {

    @NotBlank(message = "email is required")
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "email is required")
    private String username;
    @NotBlank(message = "store name is required")
    private String storeName;
    @NotBlank(message = "mobile phone is required")
    private String mobilePhone;

}

