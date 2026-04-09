/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.sis258.protocoloconversacionaltcpv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ruta Binar
 */
public class ServerConversacional {
    public static void main(String[] args) {
        int port = 5003;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor conversacional iniciado...");
            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado");
                // Lee datos enviados por el cliente
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                // Envía datos al cliente
                PrintStream toClient = new PrintStream(client.getOutputStream());
                // bucle para entender conversacion con un mismo cliente
                while (true) {
                    toClient.println("Ingrese numero 1:");
                    String r1 = fromClient.readLine();
                    if (r1 == null) {
                        break;
                    }
                    int n1 = Integer.parseInt(r1);
                    toClient.println("Ingrese numero 2:");
                    String r2 = fromClient.readLine();
                    if (r2 == null) {
                        break;
                    }
                    int n2 = Integer.parseInt(r2);
                    toClient.println("Operacion (suma, resta, multi, div) o salir:");
                    String op = fromClient.readLine();
                    if (op == null || op.equals("salir")) {
                        break;
                    }
                    int resultado = 0;
                    switch (op) {
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
                            toClient.println("Operacion invalida");
                            continue;
                    }
                    toClient.println("Resultado: " + resultado);
                }
                 client.close();  
                 System.out.println("Cliente desconectado");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
