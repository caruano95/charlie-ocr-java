import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class PantallaInput extends JPanel implements MouseListener, MouseMotionListener {

    private int oldX, oldY;
    private boolean dibujando;
    private Graphics graphics;
    int posX, posY; //para la matriz

    public static boolean preparacion[] = new boolean[64];

    PantallaInput() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void setUpDrawingGraphics() {
        graphics = getGraphics();
    }

    public void mousePressed(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();

        int width = getWidth();
        int height = getHeight();

        if (dibujando)
            return;

        else if (3 < x && x < width-3 && 3 < y && y < height-3) {
            oldX = x;
            oldY = y;
            dibujando = true;
            setUpDrawingGraphics();
        }

    }

    public void mouseReleased(MouseEvent evt) {
        if (!dibujando)
          return;
        dibujando = false;
        graphics.dispose();
        graphics = null;
    }

    public void mouseDragged(MouseEvent evt) {
        if (!dibujando)
            return;

        int x = evt.getX();
        int y = evt.getY();

        if (x < 3)
           x = 3;
        else if (x > getWidth() - 3)
           x = getWidth() - 3;

        if (y < 3)
           y = 3;
        else if (y > getHeight() - 3)
           y = getHeight() - 3;

        if(x<25) posX = 0;
        else if(x<50)  posX = 1;
        else if(x<75)  posX = 2;
        else if(x<100) posX = 3;
        else if(x<125) posX = 4;
        else if(x<150) posX = 5;
        else if(x<175) posX = 6;
        else posX = 7;

        if(y<25) posY = 0;
        else if(y<50)  posY = 1;
        else if(y<75)  posY = 2;
        else if(y<100) posY = 3;
        else if(y<125) posY = 4;
        else if(y<150) posY = 5;
        else if(y<175) posY = 6;
        else posY = 7;

        preparacion[posY*8+posX] = true;

        graphics.drawLine(oldX, oldY, x, y);  // Draw the line.

        oldX = x;
        oldY = y;
    }

    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }
    public void mouseMoved(MouseEvent evt) { }


}
