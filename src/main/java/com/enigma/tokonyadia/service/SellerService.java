package com.enigma.tokonyadia.service;

import com.enigma.tokonyadia.entity.Seller;
import com.enigma.tokonyadia.model.response.SellerResponse;

public interface SellerService {

    SellerResponse create(Seller seller);

    SellerResponse getById(String id);

    String getEmailByStoreId(String storeId);

}
