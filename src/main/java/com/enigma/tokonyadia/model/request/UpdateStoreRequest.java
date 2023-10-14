package com.enigma.tokonyadia.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UpdateStoreRequest {
    private String storeId;
    private String noSiup;
    private String storeName;
    private String address;
    private String mobilePhone;

}

