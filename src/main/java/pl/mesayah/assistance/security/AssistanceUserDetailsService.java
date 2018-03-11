package pl.mesayah.assistance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mesayah.assistance.user.User;
import pl.mesayah.assistance.user.UserRepository;

@Service
public class AssistanceUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("No user with such username in the database.");
        }

        AssistanceUserDetails userDetails =
                new AssistanceUserDetails(user, true, true, true);

        return userDetails;
    }
}
