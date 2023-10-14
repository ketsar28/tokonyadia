package com.enigma.tokonyadia.service.impl;

import com.enigma.tokonyadia.entity.Seller;
import com.enigma.tokonyadia.model.response.SellerResponse;
import com.enigma.tokonyadia.model.response.StoreResponse;
import com.enigma.tokonyadia.repository.SellerRepository;
import com.enigma.tokonyadia.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Override
    public SellerResponse create(Seller request) {
        try {
            Seller seller = sellerRepository.save(request);
            return toSellerResponse(seller);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "username already exist");
        }
    }

    @Override
    public SellerResponse getById(String id) {
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "seller not found"));
        return toSellerResponse(seller);
    }

    @Override
    public String getEmailByStoreId(String storeId) {
        Optional<Seller> store = sellerRepository.findByStoreId(storeId);
        if (store.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "seller not found");
        return store.get().getUserCredential().getEmail();
    }

    private static SellerResponse toSellerResponse(Seller seller) {
        return SellerResponse.builder()
                .username(seller.getUsername())
                .email(seller.getUserCredential().getEmail())
                .store(StoreResponse.builder()
                        .id(seller.getStore().getId())
                        .name(seller.getStore().getName())
                        .address(seller.getStore().getAddress())
                        .build())
                .build();
    }
}

