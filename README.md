Maze Generation and Solving System
Overview
This project is a robust system for generating, compressing, and solving mazes using multiple algorithms. It includes client-server communication, compression utilities, and various maze-solving strategies.

Features
Maze Generation: Supports different maze generation algorithms (e.g., EmptyMazeGenerator, SimpleMazeGenerator, MyMazeGenerator).
Maze Solving: Implements search algorithms like BreadthFirstSearch, DepthFirstSearch, and BestFirstSearch.
Compression: Custom utilities for compressing and decompressing maze data.
Client-Server Communication: Enables clients to request maze generation or solving from a server.
Configuration Management: Centralized configuration file for server settings.
Project Structure

Client
Client.java: Connects to the server and uses a strategy to interact with it.
IClientStrategy.java: Defines client-side strategies for handling server communication.
Server
Server.java: Manages client connections using a thread pool and delegates tasks to strategies.
IServerStrategy.java: Interface for server strategies.
ServerStrategyGenerateMaze.java: Generates a maze based on client input.
ServerStrategySolveSearchProblem.java: Solves a maze or retrieves a cached solution.
Configurations.java: Manages server settings via a properties file.
Maze Generation
AMazeGenerator.java: Abstract base class for maze generators.
EmptyMazeGenerator.java: Generates an empty maze.
SimpleMazeGenerator.java: Generates a maze with simple random paths.
MyMazeGenerator.java: Implements maze generation using Prim's algorithm.
Maze.java: Represents the maze structure, including start and goal positions.
Search Algorithms
ASearchingAlgorithm.java: Abstract class for search algorithms.
BreadthFirstSearch.java: Explores mazes layer by layer.
DepthFirstSearch.java: Explores mazes deeply before backtracking.
BestFirstSearch.java: Uses a priority queue to evaluate the best path first.
ISearchable.java: Interface for searchable problems.
SearchableMaze.java: Adapts the maze to be compatible with search algorithms.
Solution.java: Stores the result of a search algorithm.
Compression
SimpleCompressorOutputStream.java: Compresses data using run-length encoding.
SimpleDecompressorInputStream.java: Decompresses run-length encoded data.
MyCompressorOutputStream.java: Compresses data using binary encoding.
MyDecompressorInputStream.java: Decompresses binary-encoded data.
How to Run
Set Up the Server:

Modify the config.properties file to set the thread pool size, maze generator, and solver algorithms.
Run the Server.java file.
Run the Client:

Implement a client strategy (e.g., generate a maze, solve a maze).
Use the Client.java class to connect to the server and send requests.
Configuration
The Configurations class creates a config.properties file with the following options:

threadPoolSize: Number of threads for the server.
mazeGeneratingAlgorithm: Choose between EmptyMazeGenerator, SimpleMazeGenerator, or MyMazeGenerator.
mazeSearchingAlgorithm: Choose between BreadthFirstSearch, DepthFirstSearch, or BestFirstSearch.
Example configuration:

properties
Copy code
threadPoolSize=3
mazeGeneratingAlgorithm=MyMazeGenerator
mazeSearchingAlgorithm=BestFirstSearch
Usage Example
Client Request for Maze Generation:

The client sends the maze dimensions (e.g., 10x10) to the server.
The server generates and compresses the maze, then sends it back.
Client Request for Maze Solving:

The client sends a maze to the server.
The server solves the maze using the specified algorithm and returns the solution.
Future Enhancements
Add more maze generation algorithms.
Implement additional search algorithms like A*.
Optimize compression algorithms for better performance.
Provide a GUI for client-side operations.
How to Contribute
Fork the repository.
Create a new branch: git checkout -b feature-name.
Commit your changes: git commit -m 'Add new feature'.
Push to the branch: git push origin feature-name.
Open a pull request.
License
This project is released under the MIT License.

Author
Developed by Sahar Eitam.