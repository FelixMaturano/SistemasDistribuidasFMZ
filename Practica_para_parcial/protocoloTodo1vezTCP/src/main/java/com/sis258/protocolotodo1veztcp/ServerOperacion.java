/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.protocolotodo1veztcp;

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
public class ServerOperacion {

    public static void main(String[] args) {
        int port = 5002;

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado...");

            while (true) {
                Socket client = server.accept();
                
                    System.out.println("Cliente conectado");
                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintStream toClient = new PrintStream(client.getOutputStream());
                    while (true) {
                    String recibido = fromClient.readLine();
                    if(recibido == null){
                        System.out.println("Cliente desconectado..");
                        break;
                    }
                    System.out.println("Mensaje: " + recibido);
                    // Ejemplo esperado: "suma:5,3"
                    String[] partes = recibido.split(":");
                    String operacion = partes[0];
                    String[] numeros = partes[1].split(",");
                    int n1 = Integer.parseInt(numeros[0]);
                    int n2 = Integer.parseInt(numeros[1]);
                    int resultado = 0;

                    // Realiza la operacion segun el comando recibido
                    switch (operacion) {
                        case "suma":
                            resultado = n1 + n2;
                            break;
                        case "resta":
                            resultado = n1 - n2;
                            break;
                        case "multi":
                            resultado = n1 * n2;
                            break;
                        default:
                            toClient.println("Operacion no valida");
                            continue;
                    }
                    toClient.println(resultado);
                }
                    
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
