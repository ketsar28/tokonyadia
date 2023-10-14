package com.enigma.tokonyadia.service;

import com.enigma.tokonyadia.entity.Role;
import com.enigma.tokonyadia.entity.constant.ERole;

public interface RoleService {

    Role getOrSave(ERole role);

}