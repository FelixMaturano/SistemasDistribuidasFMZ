package com.sis258.clientholatcp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHolaTCP {

 public static void main(String[] args) {
    // Datos de conexión pública de Carlos
    String host = "problem-minneapolis.gl.joinmc.link";
    int port = 25565; 

    try {
        Socket client = new Socket(host, port); 
        System.out.println("¡Conectado exitosamente al servidor!");
        
        PrintStream toServer = new PrintStream(client.getOutputStream());
        BufferedReader fromServer = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
       
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce una cadena: ");
        String mensaje = sc.nextLine();
       
        toServer.println(mensaje);
        
        String result = fromServer.readLine();
        System.out.println("Cadena invertida recibida: " + result);

        client.close();
    } catch (IOException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
}