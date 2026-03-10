/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.chatsejercicio1;

import java.io.*;
import java.net.*;

/**
 *
 * @author Felix
 */
public class ServidorChat {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);
        System.out.println("Servidor iniciado esperando clientes");

        while (true) {
            Socket s = null;
            try {
                s = ss.accept();
                System.out.println("Nuevo Cliente conectado");

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                ManejadorCliente hilo = new ManejadorCliente(s, dis, dos);
                ManejadorCliente.listaClientes.add(hilo);
                hilo.start();

            } catch (Exception e) {
                if (s != null) {
                    s.close();
                }
                e.printStackTrace();
            }
        }
    }

}
