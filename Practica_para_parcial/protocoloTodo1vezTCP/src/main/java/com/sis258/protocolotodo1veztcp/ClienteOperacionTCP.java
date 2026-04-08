/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.protocolotodo1veztcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ruta Binar
 */
public class ClienteOperacionTCP {

    public static void main(String[] args) {
        int port = 5002;

        try {
            Socket client = new Socket("localhost", port);
            // para enviar datos al servidor
            PrintStream toServer = new PrintStream(client.getOutputStream());
            // para leer respuestas del servidor
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Scanner sc = new Scanner(System.in);
            
            while(true){
                System.out.println("Operacion (suma, resta, multi) o salir:");
                String operacion = sc.nextLine();
                
                if(operacion.equals("salir"))
                    break;
                
                System.out.println("Numero 1: ");
                int n1 = Integer.parseInt(sc.nextLine());
                
                System.out.println("Numero 2: ");
                int n2 = Integer.parseInt(sc.nextLine());
                
                // convertimos el mensaje con el formato que espera el servidor
                String mensaje = operacion + ":"+n1+","+n2;
                
                toServer.println(mensaje);
                
                String resultado = fromServer.readLine();
                System.out.println("Resultado: " + resultado);
                
            }
            
            client.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
