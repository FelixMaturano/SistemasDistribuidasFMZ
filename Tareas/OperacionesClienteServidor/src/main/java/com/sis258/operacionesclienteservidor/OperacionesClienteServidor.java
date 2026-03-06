/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.operacionesclienteservidor;

/**
 *
 * @author Felix
 */

import java.io.*;
import java.net.*;

public class OperacionesClienteServidor {

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(5056);
        System.out.println("Servidor iniciado en puerto 5056...");

        while (true) {
            Socket s = null;
            try {
                // esperar cliente
                s = ss.accept();
                System.out.println("Nuevo cliente conectado: " + s);

                // usar BufferedReader y PrintStream (igual que protocolo v2)
                BufferedReader dis = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                PrintStream dos = new PrintStream(s.getOutputStream());

                System.out.println("Asignando nuevo hilo para este cliente...");

                Thread t = new ClienteHandler(s, dis, dos);
                t.start();

            } catch (Exception e) {
                if (s != null) s.close();
                e.printStackTrace();
            }
        }
    }
}