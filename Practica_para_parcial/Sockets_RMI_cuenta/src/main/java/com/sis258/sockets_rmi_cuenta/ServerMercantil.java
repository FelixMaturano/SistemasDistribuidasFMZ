/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sockets_rmi_cuenta;

import java.net.*;
import java.io.*;
/**
 *
 * @author Ruta Binar
 */
public class ServerMercantil {
     public static void main(String[] args) throws Exception {
                 ServerSocket server = new ServerSocket(5002);
        System.out.println("Mercantil TCP listo...");
        while(true){
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream out = new PrintStream(client.getOutputStream());
            
            String ci = in.readLine();
           if (ci.equals("123")) {
                out.println("1111-5000");
            } else {
                out.println("");
            }
           client.close();
        }
     }
}
