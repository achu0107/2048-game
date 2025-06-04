package com.game2048.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckConfig {

    /**
     * Custom health indicator for game-specific checks
     */
    @Bean
    public HealthIndicator gameHealthIndicator() {
        return () -> {
            // Add your custom health checks here
            boolean gameHealthy = true; // Replace with actual checks
            
            if (gameHealthy) {
                return Health.up()
                    .withDetail("game-status", "operational")
                    .withDetail("description", "2048 game is running normally")
                    .build();
            } else {
                return Health.down()
                    .withDetail("game-status", "degraded")
                    .withDetail("issue", "Game service unavailable")
                    .build();
            }
        };
    }

    /**
     * Example database health check (if you add a database later)
     */
    @Bean
    public HealthIndicator databaseHealthIndicator() {
        return () -> Health.up()
            .withDetail("database", "mock") // Replace with real checks
            .withDetail("status", "connected")
            .build();
    }
}
