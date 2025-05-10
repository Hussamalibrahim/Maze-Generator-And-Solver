import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener , KeyListener {
    final int WIGHT = 700;
    final int HEIGHT = 700;
    static int SQUIRE = 25;
    final int col = WIGHT / SQUIRE;
    final int row = HEIGHT / SQUIRE;
    public ArrayList<Cell> cell;
    Stack<Cell> tombGrid;
    ArrayList<Cell> result = new ArrayList<>();
    Cell current;
    Timer timer;

    Panel() {
        setPreferredSize(new Dimension(WIGHT, HEIGHT));
        setBackground(Color.BLACK);
        this.addKeyListener(this);

        cell = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cell.add(new Cell(i, j));
            }
        }
        tombGrid = new Stack<>();
        current = new Cell(0, 0);
        tombGrid.add(new Cell(0, 0));
        cell.get(0).visited = true;
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


    public void draw(Graphics g) {
        cell.forEach(c -> {
            g.setColor(Color.BLACK);
            c.draw(g,Color.WHITE);
        });
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(current.x, current.y, SQUIRE, SQUIRE);
        if(!timer.isRunning())
           result.forEach(e -> e.draw(g,Color.red));
    }

    public int index(int i, int j) {
        return j + i * col;
    }

    public void checkNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        int i = current.i;
        int j = current.j;

        Cell top = (i + 1 < row) ? cell.get(index(i + 1, j)) : null;
        Cell right = (j + 1 < col) ? cell.get(index(i, j + 1)) : null;
        Cell bottom = (i > 0) ? cell.get(index(i - 1, j)) : null;
        Cell left = (j > 0) ? cell.get(index(i, j - 1)) : null;

        if (top != null && !top.visited) {
            neighbors.add(top);
        }
        if (right != null && !right.visited) {
            neighbors.add(right);
        }
        if (bottom != null && !bottom.visited) {
            neighbors.add(bottom);
        }
        if (left != null && !left.visited) {
            neighbors.add(left);
        }
        if (!neighbors.isEmpty()) {
            current.visited = true;
            int index = new Random().nextInt(neighbors.size());
            removeWall(current, neighbors.get(index));
            current = neighbors.get(index);
            tombGrid.add(current);

        } else {
            current.visited = true;
            if (!tombGrid.isEmpty())
                current = tombGrid.pop();
            else {
                dfs(cell.get(1), new ArrayList<>(), new ArrayList<>());
                timer.stop();
                repaint();

            }
        }
    }

    private void dfs(Cell currentNow, ArrayList<Cell> visitedNow, ArrayList<Cell> path) {
        visitedNow.add(currentNow);
        path.add(currentNow);
        if (currentNow.i == row -1 && currentNow.j == col -1) {
            result.addAll(path);
        }

        int i = currentNow.i;
        int j = currentNow.j;

        Cell top = (i + 1 < row) ? cell.get(index(i + 1, j)) : null;
        Cell right = (j + 1 < col) ? cell.get(index(i, j + 1)) : null;
        Cell bottom = (i > 0) ? cell.get(index(i - 1, j)) : null;
        Cell left = (j > 0) ? cell.get(index(i, j - 1)) : null;

        if (!currentNow.wall[1] && !visitedNow.contains(top) && top != null) {
            visitedNow.add(top);
            dfs(top, visitedNow, path);
        }
        if (!currentNow.wall[2] && !visitedNow.contains(right) && right != null) {
            visitedNow.add(right);
            dfs(right, visitedNow, path);
        }
        if (!currentNow.wall[0] && !visitedNow.contains(bottom) && bottom != null) {
            visitedNow.add(bottom);
            dfs(bottom, visitedNow, path);

        }
        if (!currentNow.wall[3] && !visitedNow.contains(left) && left != null) {
            visitedNow.add(left);
            dfs(left, visitedNow, path);
        }
        path.remove(path.size() - 1);
    }

    private void removeWall(Cell current, Cell cell) {
        int x = current.i - cell.i;
        int y = current.j - cell.j;
        if (x == 1) {
            current.wall[3] = false;
            cell.wall[1] = false;
        } else if (x == -1) {
            current.wall[1] = false;
            cell.wall[3] = false;
        }
        if (y == 1) {
            current.wall[0] = false;
            cell.wall[2] = false;
        } else if (y == -1) {
            current.wall[2] = false;
            cell.wall[0] = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkNeighbors();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            cell.clear();
            tombGrid.removeAllElements();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    cell.add(new Cell(i, j));
                }
            }
            current = new Cell(0, 0);
            tombGrid.add(new Cell(0, 0));
            cell.get(0).visited = true;
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
