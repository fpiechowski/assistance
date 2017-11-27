package com.github.mesayah.assistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class with starting point defined.
 */
@SpringBootApplication
public class AssistanceApplication {

    /**
     * Starting point of this application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // Start Spring Boot application and the contained server.
        SpringApplication.run(AssistanceApplication.class, args);
    }
}

