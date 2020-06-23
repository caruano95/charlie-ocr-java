
import java.io.*;
import java.util.*;

public class Image{
    private final int[] Header;
    private final String Path;
    private int Width;    
    private int Height;
    private int BitDepth;
    public boolean[] preparacion;
    
    public Image(String path) throws IOException{
        Path = path;
        Header = new int[54];
        try(FileInputStream input = new FileInputStream(this.Path)) {
            int byteActual;
            int counter = 0; 
            while(counter<54) {  
                    byteActual = input.read();
                    Header[counter]=byteActual;
                    counter++;
                    if (counter == 18) {
                            int[] bytes = new int[8];
                            for(int i=0; i<8; i++){
                                bytes[i] = input.read();
                            }
                            Width = bytes[0] | (bytes[1] << 8) | (bytes[2] << 16) | (bytes[3] << 24);
                            Height  = bytes[4] | (bytes[5] << 8) | (bytes[6] << 16) | (bytes[7] << 24); 
                            for(int i=0; i<8; i++){
                                Header[counter]=bytes[i];
                                counter++;
                            }  
                    }
                    if (counter == 28) {
                            int byte28 = input.read();
                            int byte29 = input.read();
                            BitDepth = byte28 | (byte29 << 8);
                            Header[counter]=byte28;
                            counter++;
                            Header[counter]=byte29;
                            counter++;
                    }       
            }
            preparacion = new boolean[64];
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    input.read();
                    if ((input.read()+ input.read()+ input.read() )/4 >  130)
                        preparacion[i*8+j] = true;
                }    
            }
        } 
	catch (IOException e){ 
                System.out.println("Ha ocurrido un error al leer el archivo: \n" + this.Path + e.getMessage() ); 
        }
    }

    public int getWidth(){
        return this.Width;
    }
    
    public int getHeight(){
        return this.Height;
    }
    
    
}