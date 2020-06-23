package com.charlie.ocr.util;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.io.*;
import java.util.*;


public class PantallaOut extends JPanel implements MouseListener{

    private Graphics graphics;

    PantallaOut() {
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

    public void mouseReleased(MouseEvent evt){
        try {


//Leer de el input
            NeuralNet nn = new NeuralNet(PantallaInput.preparacion);

//Leer de una imagen en la carpeta
//            Image letra = new Image("39.bmp");
//            NeuralNet nn = new NeuralNet(letra.preparacion);

            double[] resultado =  nn.forwardprop();

            for(double x : resultado) System.out.println(x);

            //Calcular el valor maximo entre todos los inputs, el mayor es el que cree que es
            int resultadofinal = 0;
            double maximo = 0.0;
            for(int i = 0; i < resultado.length; i++){
                if (resultado[i] > maximo){
                    maximo = resultado[i];
                    resultadofinal = i;
                }
            }

            String letr = String.valueOf((char)(resultadofinal + 97)); //El string de la letra que cree que es
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 300));
            graphics.drawString(letr, 0, 190); //Dibujar la letra tamaño 300

            graphics.dispose();
            graphics = null;
        }catch (IOException ex) {
            System.err.println("Error" + ex);
        }

    }


    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt) { }


}
