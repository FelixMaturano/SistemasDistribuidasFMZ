/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.clientholatcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import java.util.Scanner;

/**
 *
 * @author Felix
 */
public class ClientHolaTCP {

  public static void main(String[] args) {
       int port = 5002;
        try {
            Socket cliente= new Socket("10.8.221.101",port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            
            // Mandar un mensaje
            Scanner sc = new Scanner(System.in);
            
            System.out.print("Introduce una cadena: ");
            String mensaje = sc.nextLine();
           
            // mando al servidor el mensaje escrito
            toServer.println(mensaje);
            
            // recibo la respuesta del servidor 
            String result = fromServer.readLine();
            System.out.println("Cadena invertida: " + result);

            client.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
