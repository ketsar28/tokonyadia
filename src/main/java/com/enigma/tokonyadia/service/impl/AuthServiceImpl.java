package com.enigma.tokonyadia.service.impl;

import com.enigma.tokonyadia.entity.Customer;
import com.enigma.tokonyadia.entity.Role;
import com.enigma.tokonyadia.entity.UserCredential;
import com.enigma.tokonyadia.entity.UserDetailImpl;
import com.enigma.tokonyadia.entity.role.ERole;
import com.enigma.tokonyadia.model.request.AuthRequest;
import com.enigma.tokonyadia.model.request.RegisterSellerRequest;
import com.enigma.tokonyadia.model.response.LoginResponse;
import com.enigma.tokonyadia.model.response.RegisterResponse;
import com.enigma.tokonyadia.repository.UserCredentialRepository;
import com.enigma.tokonyadia.security.BCryptUtils;
import com.enigma.tokonyadia.security.JwtUtils;
import com.enigma.tokonyadia.service.interfaces.AuthService;
import com.enigma.tokonyadia.service.interfaces.CustomerService;
import com.enigma.tokonyadia.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
//    private final AdminRepository adminRepository;
//    private final SellerRepository sellerRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptUtils bCryptUtils;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;

    @Override
    public RegisterResponse registerCustomer(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_CUSTOMER);
            UserCredential userCredential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .roles(List.of(role))
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            Customer customer = Customer.builder()
                    .name(request.getName())
                    .address(request.getAddress())
                    .mobilePhone(request.getMobilePhone())
                    .email(request.getEmail())
                    .userCredential(userCredential)
                    .build();
            customerService.create(customer);

            return RegisterResponse.builder()
                    .email(userCredential.getEmail())
                    .build();
        }catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already exist");
        }
    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        return null;
    }

    @Override
    public RegisterResponse registerSeller(RegisterSellerRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailImpl userDetail = (UserDetailImpl) authenticate.getPrincipal();
        List<String> roles = userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String token = jwtUtils.generateToken(userDetail.getEmail());
        return LoginResponse.builder()
                .email(userDetail.getEmail())
                .roles(roles)
                .token(token)
                .build();
    }
}
