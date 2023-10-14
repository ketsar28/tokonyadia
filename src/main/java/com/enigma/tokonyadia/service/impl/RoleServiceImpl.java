package com.enigma.tokonyadia.service.impl;

import com.enigma.tokonyadia.entity.Role;
import com.enigma.tokonyadia.entity.constant.ERole;
import com.enigma.tokonyadia.repository.RoleRepository;
import com.enigma.tokonyadia.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role).orElseGet(() -> roleRepository.save(Role.builder().role(role).build()));
    }
}

