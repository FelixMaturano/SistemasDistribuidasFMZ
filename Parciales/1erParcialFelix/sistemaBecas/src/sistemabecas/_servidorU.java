/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemabecas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Ruta Binar
 */
public class _servidorU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int port = 5000;
        try {
            Socket client = new Socket("localhost", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            
            //String result = fromServer.readLine();
            
            toServer.println("1234567");
            
            String result = fromServer.readLine();
            //String respuesta = procesar(result);
            //toServer.println(result);
            
            System.out.println("cadena devuelta por el servidor es: " + result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static String procesar(String cadena){
        String[] comando = cadena.split(":");
        String datos = comando[1];
        String[] datos_personales = datos.split("-");
        if(datos_personales[0].equals("1234567")){
        return "resultado encontrado";
        }else{
            return "no encontrado";
        }
 
    }
    
}
