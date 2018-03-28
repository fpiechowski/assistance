package pl.mesayah.assistance.security.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.security.privilege.Privilege;
import pl.mesayah.assistance.security.privilege.PrivilegeRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private Role superAdmin;

    private Role projectManager;

    private Role developer;

    private Role client;

    @Autowired
    private PrivilegeRepository privilegeRepository;


    public Role getSuperAdmin() {

        return superAdmin;
    }


    public void setSuperAdmin(Role superAdmin) {

        this.superAdmin = superAdmin;
    }


    public Role getProjectManager() {

        return projectManager;
    }


    public void setProjectManager(Role projectManager) {

        this.projectManager = projectManager;
    }


    public Role getDeveloper() {

        return developer;
    }


    public void setDeveloper(Role developer) {

        this.developer = developer;
    }


    public Role getClient() {

        return client;
    }


    public void setClient(Role client) {

        this.client = client;
    }


    public void createRoles() {

        superAdmin = createSuperAdminRole();
        projectManager = createProjectManagerRole();
        developer = createDeveloperRole();
        client = createClientRole();
    }


    private Role createSuperAdminRole() {

        Collection<Privilege> privileges = (List<Privilege>) privilegeRepository.findAll();
        Role superAdmin = createAndSaveRoleIfNotExists(Role.SUPER_ADMIN, privileges);

        return superAdmin;
    }


    private Role createProjectManagerRole() {

        Collection<Privilege> privileges = new HashSet<>();
        // Find privileges of project manager

        Role projectManager = createAndSaveRoleIfNotExists(Role.PROJECT_MANAGER, privileges);

        return projectManager;
    }


    private Role createDeveloperRole() {

        Collection<Privilege> privileges = new HashSet<>();
        // Find privileges of developer

        Role developer = createAndSaveRoleIfNotExists(Role.DEVELOPER, privileges);

        return developer;
    }


    private Role createClientRole() {

        Collection<Privilege> privileges = new HashSet<>();
        // Find privileges of client

        Role client = createAndSaveRoleIfNotExists(Role.CLIENT, privileges);

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


    public Role findByName(String name) {

        return roleRepository.findByName(name);
    }
    public Set<Role> findAll() {

        return roleRepository.findAll();
    }
}
