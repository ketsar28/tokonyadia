package com.enigma.tokonyadia.service;

import com.enigma.tokonyadia.entity.ProductPrice;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);

    ProductPrice getById(String id);

    ProductPrice findProductPriceActive(String productId , Boolean active);
}
