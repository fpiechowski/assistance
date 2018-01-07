package pl.mesayah.assistance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public void createRoles() {

        createSuperAdminRole();
        createProjectManagerRole();
        createDeveloperRole();
        createClientRole();
    }

    private Role createSuperAdminRole() {

        Collection<Privilege> privileges = (Collection<Privilege>) privilegeRepository.findAll();
        Role superAdmin = createAndSaveRoleIfNotExists(Role.SUPER_ADMIN, privileges);

        return superAdmin;
    }

    private Role createProjectManagerRole() {

        Collection<Privilege> privileges = new ArrayList<>();
        // Find privileges of project manager

        Role projectManager = createAndSaveRoleIfNotExists(Role.SUPER_ADMIN, privileges);

        return projectManager;
    }

    private Role createDeveloperRole() {

        Collection<Privilege> privileges = new ArrayList<>();
        // Find privileges of developer

        Role developer = createAndSaveRoleIfNotExists(Role.SUPER_ADMIN, privileges);

        return developer;
    }

    private Role createClientRole() {

        Collection<Privilege> privileges = new ArrayList<>();
        // Find privileges of client

        Role client = createAndSaveRoleIfNotExists(Role.SUPER_ADMIN, privileges);

        return client;
    }

    private Role createAndSaveRoleIfNotExists(String roleName, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(roleName);

        if (role == null) {
            role = new Role(roleName);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }

        return role;
    }
}
