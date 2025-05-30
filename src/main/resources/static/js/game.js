// Tile colors for different values
const tileColors = {
    0: {background: "#cdc1b4", text: "#cdc1b4"},
    2: {background: "#eee4da", text: "#776e65"},
    4: {background: "#ede0c8", text: "#776e65"},
    8: {background: "#f2b179", text: "#f9f6f2"},
    16: {background: "#f59563", text: "#f9f6f2"},
    32: {background: "#f67c5f", text: "#f9f6f2"},
    64: {background: "#f65e3b", text: "#f9f6f2"},
    128: {background: "#edcf72", text: "#f9f6f2"},
    256: {background: "#edcc61", text: "#f9f6f2"},
    512: {background: "#edc850", text: "#f9f6f2"},
    1024: {background: "#edc53f", text: "#f9f6f2"},
    2048: {background: "#edc22e", text: "#f9f6f2"}
};

let bestScore = localStorage.getItem('bestScore') || 0;

async function updateBoard() {
    try {
        const response = await fetch('/api/board');
        const board = await response.json();
        
        // Update score
        document.getElementById('score').textContent = board.score;
        updateBestScore(board.score);
        
        // Create grid cells
        const gridContainer = document.getElementById('grid-container');
        gridContainer.innerHTML = '';
        for (let i = 0; i < 4; i++) {
            for (let j = 0; j < 4; j++) {
                const cell = document.createElement('div');
                cell.className = 'grid-cell';
                gridContainer.appendChild(cell);
            }
        }
        
        // Create tiles
        const tilesContainer = document.getElementById('tiles-container');
        tilesContainer.innerHTML = '';
        for (let i = 0; i < 4; i++) {
            for (let j = 0; j < 4; j++) {
                const value = board.grid[i][j].value;
                if (value !== 0) {
                    const tile = document.createElement('div');
                    tile.className = 'tile';
                    tile.textContent = value;
                    
                    // Position tile
                    tile.style.left = (j * 110 + 15) + 'px';
                    tile.style.top = (i * 110 + 15) + 'px';
                    
                    // Apply color based on value
                    const color = tileColors[value] || tileColors[0];
                    tile.style.backgroundColor = color.background;
                    tile.style.color = color.text;
                    
                    // Adjust font size for large numbers
                    if (value > 100) tile.style.fontSize = '30px';
                    if (value > 1000) tile.style.fontSize = '24px';
                    
                    tilesContainer.appendChild(tile);
                }
            }
        }
        
        // Check game over
        if (board.isGameOver) {
            showGameMessage("Game over!");
        }
        
    } catch (error) {
        console.error('Error updating board:', error);
    }
}

function updateBestScore(currentScore) {
    if (currentScore > bestScore) {
        bestScore = currentScore;
        localStorage.setItem('bestScore', bestScore);
    }
    document.getElementById('best').textContent = bestScore;
}

function showGameMessage(message) {
    const gameMessage = document.getElementById('game-message');
    gameMessage.style.display = 'flex';
    gameMessage.querySelector('p').textContent = message;
}

function hideGameMessage() {
    document.getElementById('game-message').style.display = 'none';
}

async function move(direction) {
    try {
        await fetch(`/api/move?direction=${direction}`, { method: 'POST' });
        await updateBoard();
    } catch (error) {
        console.error('Error moving:', error);
    }
}

async function reset() {
    try {
        hideGameMessage();
        await fetch('/api/reset', { method: 'POST' });
        await updateBoard();
    } catch (error) {
        console.error('Error resetting:', error);
    }
}

// Keyboard controls
document.addEventListener('keydown', (e) => {
    const keyMap = {
        'ArrowUp': 'UP',
        'ArrowLeft': 'LEFT',
        'ArrowDown': 'DOWN',
        'ArrowRight': 'RIGHT'
    };
    if (keyMap[e.key]) move(keyMap[e.key]);
});

// Initialize game
updateBoard();