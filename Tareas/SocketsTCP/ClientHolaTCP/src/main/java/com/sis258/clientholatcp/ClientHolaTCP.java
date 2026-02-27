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
            Socket client = new Socket("192.168.0.114", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Introduce una cadena: ");
            String mensaje = sc.nextLine();
            toServer.println(mensaje);
            
            String result = fromServer.readLine();
            System.out.println("cadena devuelta por el servidor es: " + result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
