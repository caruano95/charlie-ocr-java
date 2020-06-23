package com.charlie.ocr.util;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class NeuralNet{   
    int inputsLayerSize;
    int hiddenLayerSize;
    int outputLayerSize;

    double learningRate;

    double[] inputLayer;
    double[] hiddenLayer;
    double[] outputLayer;

    double[] pesos; //Pesos entre la capa input y la oculta
    double[] pesosfinal; //Pesos Oculta-Output

    //Re establece los pesos al azar ejecutando java NeuralNet    
    public static void main(String[] args) throws IOException{
        (new NeuralNet(new boolean[64])).initNet();
    }

    public NeuralNet(boolean[] iL) throws IOException{ //iL es el input
            inputsLayerSize = 65; //8x8, todos los pixeles de la imagen + bias
            hiddenLayerSize = 21; //capa media + bias
            outputLayerSize = 26; //salida, 0-A, 1-B, 2-C, etc
            learningRate = 2.0;

            inputLayer = new double[inputsLayerSize]; 
            hiddenLayer = new double[hiddenLayerSize];
            outputLayer = new double[outputLayerSize];
            pesos = new double[inputsLayerSize*hiddenLayerSize];//hay un numero de pesos igual al input para cada uno de los de en medio
            pesosfinal = new double[hiddenLayerSize*outputLayerSize]; //Igual que arriba para la capa media y el output

            //LLenando los inputs (Convirtiendo de bool a double)
            for(int i=0; i< inputsLayerSize-1; i++)
                inputLayer[i] = iL[i]? 1.0: 0.0;
            inputLayer[inputsLayerSize-1] = 1.0;//Siempre es uno para que el peso sea el bias

            //Leer del archivo para llenar los pesos 
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("first.txt")), "UTF8"));
            for(int i=0; i < hiddenLayerSize; i++)
                for(int j = 0; j < inputsLayerSize; j++)
                    pesos[(i*inputsLayerSize)+j] =  Double.parseDouble(in.readLine());

            //Cambiamos de archivo
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("second.txt")), "UTF8"));
            //Llenando los otros pesos
            for(int i=0; i< outputLayerSize; i++)
                for(int j = 0; j < hiddenLayerSize; j++)
                    pesosfinal[(i*hiddenLayerSize)+j] = Double.parseDouble(in.readLine());
            in.close();          
    }
    
    public double[] forwardprop()  throws IOException{ 
            //Forward, calcular hidden layer
            for(int i=0; i < hiddenLayerSize - 1; i++){
                double z = 0;
                for(int j = 0; j < inputsLayerSize; j++)
                    z += pesos[(i*inputsLayerSize)+j] * inputLayer[j];
                hiddenLayer[i] = sigmoid(z);
            }
            hiddenLayer[hiddenLayerSize-1] = 1.0;//Bias
            
            //Forward, calcular output
            for(int i=0; i < outputLayerSize; i++){
                double z = 0;
                for(int j = 0; j < hiddenLayerSize; j++)
                    z += pesosfinal[(i*hiddenLayerSize)+j] * hiddenLayer[j];
                outputLayer[i] = sigmoid(z);
            }

            return outputLayer;
    }

    //Cambia los pesos de acuerdo al error calculado
    public void backprop(char target, double[] out) throws IOException{ 
            
            double[] tar = new double[outputLayerSize];
            tar[((int) target) - 65] = 1; //Haciendo un arreglo con 1 en la posicion de la letra (A->tar[0]=1,B->tar[1]=1)

            //Funcion de costo, costo ≡ ∥target-output∥^2 /2 
            double costo = 0; 
            for(int i = 0; i < outputLayerSize; i++){
                costo += Math.pow((tar[i] - out[i]), 2);
            }
            costo /= 2; 
            System.out.println(costo); 

            double[] errorHidden = new double[hiddenLayerSize]; 
            double[] errorOutput = new double[outputLayerSize];

            //Backward

            //output
            for(int i=0; i < outputLayerSize; i++)
                errorOutput[i] = (tar[i] - out[i]) * (out[i] * (1.0 - out[i]));
                //-(Derivada del error) * (Derivada funcion logistica) 
            
            for(int i=0; i < outputLayerSize; i++)
                for(int j = 0; j < hiddenLayerSize; j++)
                    pesosfinal[(i*hiddenLayerSize)+j] += learningRate * errorOutput[i] * hiddenLayer[j];

            //capa media
            double sum;
            for(int i=0; i < hiddenLayerSize; i++) { 
                sum = 0.0;
                for(int j=1; j < outputLayerSize; j++)
                    sum += pesosfinal[(i*hiddenLayerSize)+j] * errorOutput[j];

                errorHidden[i] = hiddenLayer[i] * (1.0-hiddenLayer[i]) * sum;
            }

            for(int i=0; i < hiddenLayerSize; i++)
                for(int j = 0; j < inputsLayerSize; j++)
                    pesos[(i*inputsLayerSize)+j] += learningRate * errorHidden[i] * inputLayer[j];

            printNet();
    } // fin backprop
 
    //Para empezar, generamos todos los pesos al azar, pero solo una vez lo hacemos
    //Los pesos salen de nextGaussian para que sea una distribucion normal (alrededor de 0) y desviacion estandar 1 (70% de los numeros entre -1 y +1) 
    public void initNet(){
            Random r = new Random();
            try {
                PrintWriter writer = new PrintWriter("first.txt", "UTF-8");
                for(int i=0; i< hiddenLayerSize; i++)
                    for(int j = 0; j < inputsLayerSize; j++)
                        writer.println(r.nextGaussian());     

                writer.close();  
                
                PrintWriter writer2 = new PrintWriter("second.txt", "UTF-8");
                for(int i=0; i< outputLayerSize; i++)
                    for(int j = 0; j < hiddenLayerSize; j++)
                        writer2.println(r.nextGaussian());
                
                writer2.close();
            }catch (IOException ex) {
                System.err.println("Error" + ex);
            }
    }

    //Para guardar los pesos en el archivo despues de entrenar
    public void printNet(){
            try {
                PrintWriter writer = new PrintWriter("first.txt", "UTF-8");
                for(int i=0; i< hiddenLayerSize;i++)
                    for(int j = 0; j < inputsLayerSize ; j++)
                        writer.println(pesos[(i*inputsLayerSize)+j]);
                writer.close();

                PrintWriter writer2 = new PrintWriter("second.txt", "UTF-8");
                for(int i=0; i< outputLayerSize;i++)
                    for(int j = 0; j < hiddenLayerSize ; j++)
                        writer2.println(pesosfinal[(i*hiddenLayerSize)+j]);
                writer2.close();

            }catch (IOException ex) {
                System.err.println("Error" + ex);
            }
    }

    //Logistic Function
    public double sigmoid(double z){
        return ( 1.0/ (1.0+Math.exp(-z)) );
    }
}
