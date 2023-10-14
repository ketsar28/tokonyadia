package com.enigma.tokonyadia.security;

import com.enigma.tokonyadia.entity.Customer;
import com.enigma.tokonyadia.service.CustomerService;
import com.enigma.tokonyadia.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurity {

    private final SellerService sellerService;
    private final CustomerService customerService;

    public boolean checkSeller(Authentication authentication, String storeId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = sellerService.getEmailByStoreId(storeId);
        return userDetails.getUsername().equals(email);
    }

    public boolean checkCustomer(Authentication authentication, String customerId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Customer customer = customerService.getById(customerId);
        return customer.getEmail().equals(userDetails.getUsername());
    }

}

