package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taxicompany.taxicompany.domain.Role;
import ru.taxicompany.taxicompany.repository.RoleRepository;
import ru.taxicompany.taxicompany.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }

}
