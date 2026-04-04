/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.sis258.mavenproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ruta Binar
 */
public class Mavenproject1 {

    public static void main(String[] args) {

        int port = 5002;
        ServerSocket server;
        try {
            // TODO code application logic here
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito");
            Socket client;
            while (true) {

                client = server.accept(); //conexion entre cliente y servidor para comunicacion bidireccional

                System.out.println("Cliente se conecto");

                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                PrintStream toClient = new PrintStream(client.getOutputStream());

                String recibido = fromClient.readLine();
                System.out.println("El cliente envio el mensaje:" + recibido);
            
                String invertido = new StringBuilder(recibido).reverse().toString();
                toClient.println(invertido);
                
                client.close();
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
