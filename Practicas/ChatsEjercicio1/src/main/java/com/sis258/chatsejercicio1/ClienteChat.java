/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.chatsejercicio1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Felix
 */
public class ClienteChat {

    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);

            InetAddress ip = InetAddress.getByName("10.105.136.224");
            Socket s = new Socket(ip, 5056);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Thread hiloLector = new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(dis.readUTF());
                    } catch (IOException e) {
                        System.out.println("Conexión cerrada.");
                        break; // Si el servidor cierra, para el hilo
                    }
                }
            });

            hiloLector.start();

            while (true) {
                String mensaje = scn.nextLine(); // Espera que el usuario escriba
                dos.writeUTF(mensaje);           // Lo manda al servidor

                if (mensaje.equals("Exit")) {    // Si escribe "Exit", sale
                    s.close();
                    break;
                }
            }

            scn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
