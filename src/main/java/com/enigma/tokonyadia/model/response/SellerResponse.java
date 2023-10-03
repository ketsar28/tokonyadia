package com.enigma.tokonyadia.model.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SellerResponse {
    private String username;
    private String email;
    private StoreResponse store;
}
