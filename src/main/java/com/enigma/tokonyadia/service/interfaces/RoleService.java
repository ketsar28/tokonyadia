package com.enigma.tokonyadia.service.interfaces;

import com.enigma.tokonyadia.entity.Role;
import com.enigma.tokonyadia.entity.role.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}
