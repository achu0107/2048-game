package com.game2048;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {
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
}