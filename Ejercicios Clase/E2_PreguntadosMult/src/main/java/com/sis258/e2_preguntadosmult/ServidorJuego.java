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
public class ServidorJuego {

    static String[] preguntas = {
        "Cual es la capital de Francia?",
        "Cuanto es 5 x 8?",
        "En que anio llego el hombre a la luna?",
        "Cual es el planeta mas grande del sistema solar?",
        "Cuantos continentes hay en el mundo?"
    };

    static String[] respuestas = {
        "paris",
        "40",
        "1969",
        "jupiter",
        "7"
    };

    static Hashtable<String, Integer> marcador = new Hashtable<>();

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

                // Crea el hilo que atenderá a ESE jugador
                // Le pasa preguntas, respuestas y marcador compartido
                ManejadorJugador hilo = new ManejadorJugador(s, dis, dos, preguntas, respuestas, marcador);

                // Inicia el hilo, ManejadorJugador toma el control
                hilo.start();

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}