package com.charlie.ocr.util;

import com.charlie.ocr.util.NeuralNet;

import com.charlie.ocr.util.Image;
import java.io.IOException;

public class Entrenar{
    public static void main(String[] args) throws IOException{
        int trainCurr = 0;
        int letraCurr = 0;

        while(trainCurr<200){  //De los 200 samples de letras, en cual vamos
            while(letraCurr < 26){
                char letr = (char)(letraCurr + 65); //En que letra vamos dentro de la carpeta
            
                Image letra;
                letra = new Image("TrainingBMP/" + letr + "/" + trainCurr + ".bmp");
                NeuralNet nn = new NeuralNet(letra.preparacion);
                //forward propagation y despues backpropagation para cambiar los pesos/bias
                nn.backprop(letr, nn.forwardprop()); 
                
                letraCurr++;
                //No tengo muestras ni de I ni de J ni de L
                if(letraCurr == 8) letraCurr+=2; 
                if(letraCurr == 11) letraCurr++;
                if(letraCurr == 19) letraCurr++;//no W porque arruinan todo
            }
            letraCurr = 0;
            trainCurr++;
        }
    }
}