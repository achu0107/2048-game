package com.game2048;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testInitialBoardHasTwoNonZeroTiles() {
        int nonZeroCount = 0;
        for (Tile[] row : board.getGrid()) {
            for (Tile tile : row) {
                if (tile.getValue() != 0) nonZeroCount++;
            }
        }
        assertEquals(2, nonZeroCount);
    }

    @Test
    void testMoveUpMergesTiles() {
        // Setup test scenario
        Tile[][] testGrid = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                testGrid[i][j] = new Tile(0);
            }
        }
        testGrid[1][0] = new Tile(2);
        testGrid[3][0] = new Tile(2);
        board.setGrid(testGrid);

        board.move(Direction.UP);
        assertEquals(4, board.getGrid()[0][0].getValue());
    }
}