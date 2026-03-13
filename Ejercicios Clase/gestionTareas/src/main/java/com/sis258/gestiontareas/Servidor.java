/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.gestiontareas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Felix
 */
public class Servidor {
         public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5058);
        System.out.println("Servidor de Tareas iniciado en puerto 5058");

        while (true) {
            Socket s = null;
            try {
                s = ss.accept();
                System.out.println("Cliente conectado: " + s.getInetAddress());
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                ManejadorSolicitud hilo = new ManejadorSolicitud(s, dis, dos);
                hilo.start();
            } catch (Exception e) {
                s.close(); e.printStackTrace();
            }
        }
    }
}


