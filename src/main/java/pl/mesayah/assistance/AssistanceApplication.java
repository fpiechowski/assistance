package pl.mesayah.assistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.mesayah.assistance.security.VaadinSessionSecurityContextHolderStrategy;

/**
 * Main application class with starting point defined.
 */
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
public class AssistanceApplication {

    public static final String NAME = "Assistance";

    /**
     * Starting point of this application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // Start Spring Boot application and the contained server.
        SpringApplication.run(AssistanceApplication.class, args);
    }

    @Configuration
    @EnableGlobalMethodSecurity(securedEnabled = true)
    public static class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

        static {
            SecurityContextHolder.setStrategyName(VaadinSessionSecurityContextHolderStrategy.class.getName());
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.inMemoryAuthentication()
                    .withUser("admin").password("admin")
                    .roles("SUPER_USER", "PROJECT_MASTER", "DEVELOPER", "CLIENT")
                    .and()
                    .withUser("user").password("user").roles("DEVELOPER");
        }

        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {

            return authenticationManager();
        }
    }
}

