package com.enigma.tokonyadia.model.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SellerRequest {
    private String username;
    private String email;
    private String storeName;
    private String mobilePhone;
}

