/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.gestiontareas;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTareas {
    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 5058);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Conectado al Sistema de Gestion de Tareas");

            Thread receptor = new Thread(() -> {
                try {
                    while (true) { System.out.println(dis.readUTF()); }
                } catch (IOException e) {
                    System.out.println("Conexion cerrada.");
                }
            });
            receptor.start();

            while (receptor.isAlive()) {
                String comando = scn.nextLine();
                dos.writeUTF(comando);
                if (comando.equalsIgnoreCase("Exit")) break;
            }
            scn.close(); s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

