# 🌀 Maze Generator & Solver

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

![Swing](https://img.shields.io/badge/GUI-Java_Swing-007396)

A Java Swing application that generates random mazes using Depth-First Search and solves them automatically.

![Maze Generation Demo](https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExcDl2dWl3aXJ1b3RqY2R4dWY0dGJ3eWxqZ2N5b2VjZzV4eGZ2ZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/your-demo-gif-id.gif)

## Features

- 🧱 Generates perfect mazes using Randomized DFS
- 🔍 Solves mazes automatically with DFS algorithm
- 🎨 Visualizes both generation and solving processes
- ⏱️ 60 FPS smooth animation
- 🔄 Reset with spacebar for new mazes

# Code Structure

```bash
# Core Components
// Maze Cell Representation
public class Cell {
    boolean[] wall = {true, true, true, true}; // Top, Right, Bottom, Left
    boolean visited = false;
    // ... drawing logic
}
// Maze Generation Engine
public class Panel extends JPanel implements ActionListener {
    final int WIDTH = 700, HEIGHT = 700;
    static int SQUARE_SIZE = 25;
    ArrayList<Cell> grid = new ArrayList<>();
    Stack<Cell> stack = new Stack<>();
    // ... maze generation and solving logic
}
```
# Algorithms
- Maze Generation
```
// Randomized Depth-First Search
public void checkNeighbors() {
    ArrayList<Cell> neighbors = new ArrayList<>();
    // Find unvisited neighbors
    if (hasUnvisitedNeighbors()) {
        Cell next = getRandomNeighbor();
        removeWalls(current, next);
        stack.push(current);
        current = next;
    } else if (!stack.isEmpty()) {
        current = stack.pop();
    }
}
```
- Path Solving
 ```
// DFS Pathfinding
private void dfs(Cell current, ArrayList<Cell> path) {
    if (reachedEnd(current)) {
        solutionPath = new ArrayList<>(path);
        return;
    }
    // Explore neighbors through open walls
    for (Cell neighbor : getAccessibleNeighbors(current)) {
        if (!visited.contains(neighbor)) {
            path.add(neighbor);
            dfs(neighbor, path);
            path.remove(path.size()-1);
        }
    }
}
```
# Customization
Modify these constants in Panel.java:
```
final int WIDTH = 700;       // Window width
final int HEIGHT = 700;      // Window height
static int SQUARE_SIZE = 25; // Cell size (affects maze complexity)
```
® Created by Hussam Alibrahim

