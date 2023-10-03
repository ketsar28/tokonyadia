package com.enigma.tokonyadia.service.interfaces;

import com.enigma.tokonyadia.entity.ProductPrice;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);

    ProductPrice getById(String id);

    ProductPrice findProductPriceActive(String productId , Boolean active);
}
