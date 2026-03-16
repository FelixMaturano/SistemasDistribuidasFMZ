/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.practicasocketsrmi;


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
public class ServerMercantil {
    ///aqui pegar servidor tcp
    ///
   public static void main(String[] args) {
        int port = 5002;
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            System.out.println("--- Servidor Banco Mercantil (TCP) Iniciado en el puerto " + port + " ---");
            System.out.println("Esperando conexiones de Justicia...");
            
            while (true) {
                Socket client = server.accept(); // Se acepta la conexión TCP
                System.out.println("\n[Mercantil] Cliente se conecto desde: " + client.getInetAddress());
                
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); 
                String recibido = fromClient.readLine();
                System.out.println("[Mercantil] Mensaje recibido: " + recibido);
                
                // 1. Preparamos la respuesta por defecto (cadena vacía si no hay datos)
                String response = ""; 
                
                if (recibido != null) {
                    // 2. Verificamos si el mensaje contiene el CI del Caso de Prueba Obligatorio
                    // Usamos .contains() por si Justicia envía solo "11021654" o "Consultar:11021654"
                    if (recibido.contains("11021654")) {
                        // 3. Formato requerido: cuenta-saldo
                        response = "1515-5200.0"; 
                    }
                }
                
                // 4. Enviamos la respuesta de vuelta al cliente
                PrintStream toClient = new PrintStream(client.getOutputStream());
                toClient.println(response);
                System.out.println("[Mercantil] Respuesta enviada: " + (response.isEmpty() ? "(vacia)" : response));
                
                // 5. ¡IMPORTANTE EN TCP! Cerramos la conexión con este cliente específico
                // para liberar memoria y recursos.
                client.close();
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
