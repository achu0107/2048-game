# 2048-game
2048 Game - Spring Boot Implementation
Java
Spring Boot
Maven

A complete implementation of the popular 2048 game using Java and Spring Boot, featuring a responsive web interface and REST API backend.

2048-game/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── game2048/
│   │   │           ├── Board.java         # Game board logic
│   │   │           ├── Direction.java     # Movement direction enum
│   │   │           ├── Game2048Application.java # Spring Boot main class
│   │   │           ├── GameController.java# REST API controller
│   │   │           └── Tile.java          # Tile representation
│   │   └── resources/
│   │       ├── application.properties      # Spring configuration
│   │       └── static/
│   │           ├── index.html             # Main game interface
│   │           └── js/
│   │               └── game.js            # Game logic and UI handling
│   └── test/
│       └── java/
│           └── com/
│               └── game2048/
│                   ├── BoardTest.java      # Unit tests for game logic
│                   └── GameControllerTest.java # Integration tests for API
├── pom.xml                                # Maven configuration
└── README.md                              # Project documentation


Installation Prerequisites
Java 11 or higher
Maven 3.6.0 or higher
Git (optional)

Steps:
Clone the repository:
git clone https://github.com/your-username/2048-game.git
cd 2048-game


Build the project with Maven:
mvn clean package

Running the Application from Command Line

java -jar target/game2048-1.0-SNAPSHOT.jar

Accessing the Game [Open your web browser and navigate to - http://localhost:8080]

Running Tests
mvn test
