/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.e2_preguntadosmult;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Felix
 */
public class ClienteJuego {

    public static void main(String[] args) {
        try {
            Scanner scn = new Scanner(System.in);

            // Conectarse al servidor, cambiar IP si es en red local
            // En red local usar IP del compañero: 10.105.136.224
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 5056);

            // Canales de comunicación con el servidor
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Conectado al servidor de juego!");

            // El cliente solo recibe y responde, no necesita hilo extra
            // porque el juego es turno a turno: pregunta → respuesta
            while (true) {
                // Recibe mensaje del servidor (pregunta, resultado o fin)
                String mensaje = dis.readUTF();
                System.out.println(mensaje);

                // Si el juego terminó, sale del bucle
                if (mensaje.contains("Juego terminado") || mensaje.contains("MARCADOR")) {
                    if (mensaje.contains("MARCADOR")) {
                        break; // Sale cuando recibe el marcador final
                    }
                    continue;
                }

                // Si es una pregunta, espera que el jugador responda
                if (mensaje.contains("Pregunta") || mensaje.contains("nombre")) {
                    String respuesta = scn.nextLine();
                    dos.writeUTF(respuesta); // Manda la respuesta al servidor
                }
            }

            System.out.println("Gracias por jugar!");
            s.close();
            scn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
