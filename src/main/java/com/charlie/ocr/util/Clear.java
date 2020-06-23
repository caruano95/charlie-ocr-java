package com.charlie.ocr.util;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;


public class Clear extends JPanel implements MouseListener{   

    private Graphics graphics;  
    
    Clear() {
        addMouseListener(this);
    }
          
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
    } 
    

    private void setUpDrawingGraphics() {
        graphics = getGraphics();   
    }

    public void mousePressed(MouseEvent evt) {
            setUpDrawingGraphics();
    }
    
    public void mouseReleased(MouseEvent evt) {
        Letras.in.repaint();
        Letras.prep.repaint();
        Letras.out.repaint();
        PantallaInput.preparacion = new boolean[64];

        graphics.dispose();
        graphics = null;
    }


    public void mouseEntered(MouseEvent evt) { }  
    public void mouseExited(MouseEvent evt) { }    
    public void mouseClicked(MouseEvent evt) { }   
    
  
}
