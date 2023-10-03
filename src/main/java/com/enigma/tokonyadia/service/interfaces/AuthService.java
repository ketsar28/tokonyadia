package com.enigma.tokonyadia.service.interfaces;

import com.enigma.tokonyadia.model.request.AuthRequest;
import com.enigma.tokonyadia.model.request.RegisterSellerRequest;
import com.enigma.tokonyadia.model.response.LoginResponse;
import com.enigma.tokonyadia.model.response.RegisterResponse;

public interface AuthService {

    RegisterResponse registerCustomer(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    RegisterResponse registerSeller(RegisterSellerRequest request);
    LoginResponse login(AuthRequest request);

}
