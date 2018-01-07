package pl.mesayah.assistance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public void createPrivileges() {

        createIssuePrivileges();
        createMessagingPrivileges();
        createMilestonePrivileges();
        createProjectPrivileges();
        createTaskPrivileges();
        createTeamPrivileges();
        createPersonalTaskPrivileges();
        createUserPrivileges();

        // TODO: Define all needed privileges.
    }

    private void createIssuePrivileges() {

        createAndSavePrivilegeIfNotExists("CREATE_ISSUE");
        createAndSavePrivilegeIfNotExists("READ_ISSUE");
        createAndSavePrivilegeIfNotExists("UPDATE_ISSUE");
        createAndSavePrivilegeIfNotExists("DELETE_ISSUE");
    }

    private void createMessagingPrivileges() {

        createAndSavePrivilegeIfNotExists("SEND_MESSAGE");
        createAndSavePrivilegeIfNotExists("RECEIVE_MESSAGE");
        createAndSavePrivilegeIfNotExists("UPDATE_MESSAGE");
        createAndSavePrivilegeIfNotExists("DELETE_MESSAGE");
    }

    private void createMilestonePrivileges() {

        createAndSavePrivilegeIfNotExists("CREATE_MILESTONE");
        createAndSavePrivilegeIfNotExists("READ_MILESTONE");
        createAndSavePrivilegeIfNotExists("UPDATE_MILESTONE");
        createAndSavePrivilegeIfNotExists("DELETE_MILESTONE");
    }

    private void createProjectPrivileges() {

        createAndSavePrivilegeIfNotExists("CREATE_PROJECT");
        createAndSavePrivilegeIfNotExists("READ_PROJECT");
        createAndSavePrivilegeIfNotExists("UPDATE_PROJECT");
        createAndSavePrivilegeIfNotExists("DELETE_PROJECT");
    }

    private void createTaskPrivileges() {

        createAndSavePrivilegeIfNotExists("CREATE_TASK");
        createAndSavePrivilegeIfNotExists("READ_TASK");
        createAndSavePrivilegeIfNotExists("UPDATE_TASK");
        createAndSavePrivilegeIfNotExists("DELETE_TASK");
    }

    private void createTeamPrivileges() {

        createAndSavePrivilegeIfNotExists("CREATE_TEAM");
        createAndSavePrivilegeIfNotExists("READ_TEAM");
        createAndSavePrivilegeIfNotExists("UPDATE_TEAM");
        createAndSavePrivilegeIfNotExists("DELETE_TEAM");
    }

    private void createPersonalTaskPrivileges() {

        createAndSavePrivilegeIfNotExists("CREATE_PERSONAL_TASK");
        createAndSavePrivilegeIfNotExists("READ_PERSONAL_TASK");
        createAndSavePrivilegeIfNotExists("UPDATE_PERSONAL_TASK");
        createAndSavePrivilegeIfNotExists("DELETE_PERSONAL_TASK");
    }

    private void createUserPrivileges() {

        createAndSavePrivilegeIfNotExists("CREATE_USER");
        createAndSavePrivilegeIfNotExists("READ_USER");
        createAndSavePrivilegeIfNotExists("UPDATE_USER");
        createAndSavePrivilegeIfNotExists("DELETE_USER");
    }

    private Privilege createAndSavePrivilegeIfNotExists(String privilegeName) {

        Privilege privilege = privilegeRepository.findByName(privilegeName);
        if (privilege == null) {
            privilege = new Privilege(privilegeName);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
}
