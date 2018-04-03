package pl.mesayah.assistance.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mesayah.assistance.security.privilege.Privilege;
import pl.mesayah.assistance.security.role.Role;
import pl.mesayah.assistance.user.User;

import java.util.ArrayList;
import java.util.Collection;

public class AssistanceUserDetails implements UserDetails {

    private User user;

    private boolean credentialNonExpired = true;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;


    public AssistanceUserDetails(User user, boolean credentialNonExpired, boolean accountNonExpired, boolean accountNonLocked) {

        this.user = user;
        this.credentialNonExpired = credentialNonExpired;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
    }


    public AssistanceUserDetails(User user) {

        this.user = user;
    }

    public Long getID(){
        return user.getId();
    }

    public User getUser() {

        return user;
    }


    public void setUser(User user) {

        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role r : user.getRoles()) {
            for (Privilege p : r.getPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            }
        }

        return authorities;
    }


    @Override
    public String getPassword() {

        return user.getPassword();
    }


    @Override
    public String getUsername() {

        return user.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {

        return accountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {

        return accountNonLocked;
    }


    @Override
    public boolean isCredentialsNonExpired() {

        return credentialNonExpired;
    }


    @Override
    public boolean isEnabled() {

        return user.isEnabled();
    }
}
