import java.awt.*;

public class Cell {
    int i;
    int j;
    int x;
    int y;
    final int SQUIRE = Panel.SQUIRE;
    boolean[] wall = {true, true, true, true};
    boolean visited = false;

    Cell(int i, int j) {
        this.i = i;
        this.j = j;
        x = i * SQUIRE;
        y = j * SQUIRE;
    }

    public void draw(Graphics g, Color color) {
        //bottom
        if (wall[0]) g.drawLine(x, y, x + SQUIRE, y);
        //up
        if (wall[1]) g.drawLine(x, y + SQUIRE, x + SQUIRE, y + SQUIRE);
        //right
        if (wall[2]) g.drawLine(x + SQUIRE, y, x + SQUIRE, y + SQUIRE);
        //left
        if (wall[3]) g.drawLine(x, y, x, y + SQUIRE);
        if (visited) {
            g.setColor(color);
            g.fillRect(x, y, SQUIRE, SQUIRE);
        }
    }

}
