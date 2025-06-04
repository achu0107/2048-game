package com.game2048;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class GameController implements HealthIndicator {

    private Board board = new Board();

    @GetMapping("/board")
    public Board getBoard() {
        return board;
    }

    @PostMapping("/move")
    public Board move(@RequestParam String direction) {
        board.move(Direction.valueOf(direction.toUpperCase()));
        return board;
    }

    @PostMapping("/reset")
    public Board reset() {
        board = new Board();
        return board;
    }

    @GetMapping("/health")
    public Health customHealthCheck() {
        return health();  // reuse Spring Boot's built-in health logic
    }

    @Override
    public Health health() {
        try {
            return Health.up()
                    .withDetail("game", "running")
                    .withDetail("boardStatus", "healthy")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
