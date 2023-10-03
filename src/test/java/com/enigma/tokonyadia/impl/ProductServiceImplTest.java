package com.enigma.tokonyadia.impl;

import com.enigma.tokonyadia.entity.Product;
import com.enigma.tokonyadia.entity.ProductPrice;
import com.enigma.tokonyadia.entity.Store;
import com.enigma.tokonyadia.model.request.ProductRequest;
import com.enigma.tokonyadia.model.response.ProductResponse;
import com.enigma.tokonyadia.repository.ProductRepository;
import com.enigma.tokonyadia.service.impl.ProductServiceImpl;
import com.enigma.tokonyadia.service.interfaces.ProductPriceService;
import com.enigma.tokonyadia.service.interfaces.ProductService;
import com.enigma.tokonyadia.service.interfaces.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductPriceService productPriceService = mock(ProductPriceService.class);
    private final StoreService storeService = mock(StoreService.class);

    private final ProductService productService = new ProductServiceImpl(productRepository,storeService,productPriceService);

    @BeforeEach
    public void setUp(){
        //reset mock behavior
        reset(productRepository,storeService,productPriceService);
    }

    @Test
    void createProduct() {
        //data dummy request
        ProductRequest dummyRequest = new ProductRequest();
        dummyRequest.setProductId("product123");
        dummyRequest.setStoreId("store123");
        dummyRequest.setProductName("Oreo");
        dummyRequest.setDescription("Hitam Manis");
        dummyRequest.setPrice(100L);
        dummyRequest.setStock(10);

        //data store dummy
        Store dummyStore = new Store();
        dummyStore.setId("store123");
        dummyStore.setName("Berkah Selalu");
        dummyStore.setAddress("Ragunan");

        //data dummy Product
        Product dummyProduct = new Product();
        dummyProduct.setId("product123");
        dummyProduct.setName(dummyRequest.getProductName());
        dummyProduct.setDescription(dummyRequest.getDescription());

        //data dummy Price
        ProductPrice dummyProductPrice = new ProductPrice();
        dummyProductPrice.setPrice(dummyRequest.getPrice());
        dummyProductPrice.setStock(dummyRequest.getStock());

        //Mock
        when(storeService.getById(dummyRequest.getStoreId())).thenReturn(dummyStore);
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(dummyProduct);
        when(productPriceService.create(any(ProductPrice.class))).thenReturn(dummyProductPrice);

        ProductResponse createProduct = productService.createProduct(dummyRequest);

        //verify
        verify(storeService, times(1)).getById(dummyRequest.getStoreId());
        verify(productRepository,times(1)).saveAndFlush(any(Product.class));
        verify(productPriceService,times(1)).create(any(ProductPrice.class));

        //assert
        assertEquals(dummyProduct.getName() , createProduct.getProductName());
        assertEquals(dummyProductPrice.getPrice() , createProduct.getPrice());
        assertEquals(dummyStore.getName() , createProduct.getStore().getName());
    }
}