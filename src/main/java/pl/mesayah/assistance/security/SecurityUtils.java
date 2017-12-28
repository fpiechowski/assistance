package pl.mesayah.assistance.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * A static utils class for common security checks.
 */
public final class SecurityUtils {

    /**
     * A private constructor to prevent creating instances of this utils class.
     */
    private SecurityUtils() {

    }

    /**
     * Checks if there is a signed in user for the session.
     *
     * @return true if there is a signed in user in the session
     */
    public static boolean isLoggedIn() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Checks if a currently signed in user has a given role.
     * @param role a role to check for a currently signed in user
     * @return true if a currently signed in user has a given role
     */
    public static boolean hasRole(String role) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
    }
}
