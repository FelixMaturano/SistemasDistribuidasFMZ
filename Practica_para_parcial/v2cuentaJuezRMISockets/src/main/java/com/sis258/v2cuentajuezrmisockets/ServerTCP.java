/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.v2cuentajuezrmisockets;
import java.net.*;
import java.io.*;

/**
 *
 * @author Ruta Binar
 */
public class ServerTCP {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Banco A (TCP) listo...");
        while (true) {
            Socket cliente = server.accept();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(cliente.getInputStream())
            );

            PrintStream out = new PrintStream(cliente.getOutputStream());
            String ci = in.readLine();
            if (ci.equals("123")) {
                out.println("111-5000");
            } else {
                out.println("");
            }
            cliente.close();
        }
    }

}
