/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabecas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author PC
 */
public class SegipTCP {

    public static void main(String[] args){
        int port = 5000;
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con exito");
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion entre cliente y servidor para comunicacion bidireccional
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
             toClient = new PrintStream(client.getOutputStream());
            
            String recibido = fromClient.readLine();
            System.out.println("Cliente envio: " + recibido);
            
            if(recibido.equals("1234567")){
                toClient.println("Juana gomez si existe");
            }
            //System.out.println("El cliente envio el mensaje:" + recibido);
            
            
            
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
