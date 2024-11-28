# MazeGame

## 📜 Description
MazeGame is a Java-based project designed to generate, solve, and analyze mazes. This project implements various algorithms for maze generation and solving, utilizing client-server architecture and efficient data structures.

The game also includes testing tools, configuration options, and a modular design for easy extension.

---

## ✨ Features
- **Maze Generation**: Create mazes using efficient algorithms like Depth-First Search (DFS) and more.
- **Maze Solving**: Solve mazes using BFS, DFS, and Best-First Search algorithms.
- **Client-Server Architecture**: Interact with a server to generate or solve mazes remotely.
- **Compression/Decompression**: Compress and decompress mazes for storage and transmission.
- **Testing Tools**: Includes JUnit tests for validating functionality.

---

## 🛠️ How to Run
1. **Setup the Environment**:
   - Install Java 8 or higher.
   - Use an IDE like IntelliJ IDEA or Eclipse for running the project.

2. **Run the Server**:
   - Navigate to `ATP-PartB/src/Server`.
   - Execute `Server.java` to start the server.

3. **Run the Client**:
   - Navigate to `ATP-PartB/src/Client`.
   - Execute `Client.java` to interact with the server.

4. **Configuration**:
   - Update `config.properties` in the `resources` folder for custom server settings.

---

## 📂 Project Structure
```plaintext
MazeGame/
├── ATP-PartB/
│   ├── src/               # Source code
│   │   ├── algorithms/    # Maze algorithms and solving logic
│   │   ├── Client/        # Client-side code
│   │   ├── Server/        # Server-side code
│   │   ├── IO/            # Input/Output utilities
│   │   ├── test/          # Testing scripts
│   ├── resources/         # Configuration files
│   ├── JUnit/             # Unit tests
│   └── README.md          # Project documentation
