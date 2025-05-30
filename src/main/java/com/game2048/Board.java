package com.game2048;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Random;

public class Board {
    private Tile[][] grid;
    private int size;
    @JsonIgnore
    private Random random;
    private int score;

    public Board() {
        this(4); // Default to 4x4 board
    }

    public Board(int size) {
        this.size = size;
        this.grid = new Tile[size][size];
        this.random = new Random();
        this.score = 0;
        initializeBoard();
        addRandomTile();
        addRandomTile();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Tile(0);
            }
        }
    }

    void addRandomTile() {
        int emptyCells = 0;
        for (Tile[] row : grid) {
            for (Tile tile : row) {
                if (tile.getValue() == 0) emptyCells++;
            }
        }
        if (emptyCells == 0) return;

        int position = random.nextInt(emptyCells);
        int value = random.nextInt(10) < 9 ? 2 : 4;
        int count = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getValue() == 0) {
                    if (count == position) {
                        grid[i][j].setValue(value);
                        return;
                    }
                    count++;
                }
            }
        }
    }

    public void move(Direction direction) {
        boolean moved = false;
        switch (direction) {
            case UP: moved = moveUp(); break;
            case DOWN: moved = moveDown(); break;
            case LEFT: moved = moveLeft(); break;
            case RIGHT: moved = moveRight(); break;
        }
        if (moved) addRandomTile();
    }

    private boolean moveUp() {
        boolean moved = false;
        for (int j = 0; j < size; j++) {
            for (int i = 1; i < size; i++) {
                if (grid[i][j].getValue() != 0) {
                    int currentRow = i;
                    while (currentRow > 0 && 
                          (grid[currentRow-1][j].getValue() == 0 || 
                           grid[currentRow-1][j].getValue() == grid[currentRow][j].getValue())) {
                        if (grid[currentRow-1][j].getValue() == 0) {
                            grid[currentRow-1][j].setValue(grid[currentRow][j].getValue());
                            grid[currentRow][j].setValue(0);
                            moved = true;
                        } else if (grid[currentRow-1][j].getValue() == grid[currentRow][j].getValue()) {
                            grid[currentRow-1][j].setValue(grid[currentRow-1][j].getValue() * 2);
                            grid[currentRow][j].setValue(0);
                            score += grid[currentRow-1][j].getValue();
                            moved = true;
                            break;
                        }
                        currentRow--;
                    }
                }
            }
        }
        return moved;
    }

    private boolean moveDown() {
        boolean moved = false;
        for (int j = 0; j < size; j++) {
            for (int i = size-2; i >= 0; i--) {
                if (grid[i][j].getValue() != 0) {
                    int currentRow = i;
                    while (currentRow < size-1 && 
                          (grid[currentRow+1][j].getValue() == 0 || 
                           grid[currentRow+1][j].getValue() == grid[currentRow][j].getValue())) {
                        if (grid[currentRow+1][j].getValue() == 0) {
                            grid[currentRow+1][j].setValue(grid[currentRow][j].getValue());
                            grid[currentRow][j].setValue(0);
                            moved = true;
                        } else if (grid[currentRow+1][j].getValue() == grid[currentRow][j].getValue()) {
                            grid[currentRow+1][j].setValue(grid[currentRow+1][j].getValue() * 2);
                            grid[currentRow][j].setValue(0);
                            score += grid[currentRow+1][j].getValue();
                            moved = true;
                            break;
                        }
                        currentRow++;
                    }
                }
            }
        }
        return moved;
    }

    private boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (grid[i][j].getValue() != 0) {
                    int currentCol = j;
                    while (currentCol > 0 && 
                          (grid[i][currentCol-1].getValue() == 0 || 
                           grid[i][currentCol-1].getValue() == grid[i][currentCol].getValue())) {
                        if (grid[i][currentCol-1].getValue() == 0) {
                            grid[i][currentCol-1].setValue(grid[i][currentCol].getValue());
                            grid[i][currentCol].setValue(0);
                            moved = true;
                        } else if (grid[i][currentCol-1].getValue() == grid[i][currentCol].getValue()) {
                            grid[i][currentCol-1].setValue(grid[i][currentCol-1].getValue() * 2);
                            grid[i][currentCol].setValue(0);
                            score += grid[i][currentCol-1].getValue();
                            moved = true;
                            break;
                        }
                        currentCol--;
                    }
                }
            }
        }
        return moved;
    }

    private boolean moveRight() {
        boolean moved = false;
        for (int i = 0; i < size; i++) {
            for (int j = size-2; j >= 0; j--) {
                if (grid[i][j].getValue() != 0) {
                    int currentCol = j;
                    while (currentCol < size-1 && 
                          (grid[i][currentCol+1].getValue() == 0 || 
                           grid[i][currentCol+1].getValue() == grid[i][currentCol].getValue())) {
                        if (grid[i][currentCol+1].getValue() == 0) {
                            grid[i][currentCol+1].setValue(grid[i][currentCol].getValue());
                            grid[i][currentCol].setValue(0);
                            moved = true;
                        } else if (grid[i][currentCol+1].getValue() == grid[i][currentCol].getValue()) {
                            grid[i][currentCol+1].setValue(grid[i][currentCol+1].getValue() * 2);
                            grid[i][currentCol].setValue(0);
                            score += grid[i][currentCol+1].getValue();
                            moved = true;
                            break;
                        }
                        currentCol++;
                    }
                }
            }
        }
        return moved;
    }

    public boolean isGameOver() {
        // Check for empty cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getValue() == 0) {
                    return false;
                }
            }
        }

        // Check for possible merges
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if ((i < size - 1 && grid[i][j].getValue() == grid[i + 1][j].getValue()) ||
                    (j < size - 1 && grid[i][j].getValue() == grid[i][j + 1].getValue())) {
                    return false;
                }
            }
        }

        return true;
    }

    public Tile[][] getGrid() {
        return grid;
    }
    
    public void setGrid(Tile[][] grid) {
        this.grid = grid;
    }

    public int getScore() {
        return score;
    }
}