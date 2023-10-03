package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> , JpaSpecificationExecutor<Product> {
}
