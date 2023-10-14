package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

    Optional<Seller> findByStoreId(String storeId);

}
