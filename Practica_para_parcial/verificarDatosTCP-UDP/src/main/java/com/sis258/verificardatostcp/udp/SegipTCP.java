/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.sis258.verificardatostcp.udp;
import java.io.*;
import java.net.*;
import java.net.ServerSocket;
/**
 *
 * @author Ruta Binar
 */
public class SegipTCP {
    public static void main(String[] args) {
        int port = 5000;
        
        try{
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor Segip iniciado .....");
            Socket client = server.accept();
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
            PrintStream toClient = new PrintStream(client.getOutputStream());
            String recibido = fromClient.readLine();
            System.out.println("Segip recibi: " + recibido);
            
            String[] cadena = recibido.split(":");
            if(cadena[1].equals("1234567")){
                toClient.println("Si existe " );
            }else{
                toClient.println("No existe");
            }
            //toClient.println("ENVIADO DESDE EL SERVIDOR");
            client.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
                 
        }
    }
    
}
