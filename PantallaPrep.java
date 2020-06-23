import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrep extends JPanel implements MouseListener{

    private Graphics graphics;

    PantallaPrep() {
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void mousePressed(MouseEvent evt) {
        graphics = getGraphics();
    }

    public void mouseReleased(MouseEvent evt) {
        for(int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                if (PantallaInput.preparacion[j*8+i])
                    graphics.fillRect(i*25, j*25, 25, 25);
        graphics.dispose();
        graphics = null;
        print();
    }

    public void print(){
        BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        for(int i=0; i<8; i++)
            for (int j=0; j<8; j++)
                if (PantallaInput.preparacion[j*8+i])
                    g.fillRect(i*25, j*25, 25, 25);
        this.paint(g);  //this == JComponent
        g.dispose();
        try{ImageIO.write(bi,"png",new File("test.png"));}catch (Exception e) {}
    }

    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }


}
