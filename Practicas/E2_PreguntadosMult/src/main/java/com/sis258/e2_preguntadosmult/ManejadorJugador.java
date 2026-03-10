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
public class ManejadorJugador extends Thread {// Es un hilo por eso "extends Thread"

    Socket s;                // Conexion con el cliente
    DataInputStream dis;     //Canal para leer 
    DataOutputStream dos;
    String nombre;
    String[] preguntas;
    String[] respuestas;
    Hashtable<String, Integer> marcador;

    public ManejadorJugador(Socket s, DataInputStream dis, DataOutputStream dos,
            String[] preguntas, String[] respuestas,
            Hashtable<String, Integer> marcador) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.marcador = marcador;
    }

    @Override
    public void run() {
        try {
            dos.writeUTF("Escribe tu nombre: ");
            nombre = dis.readUTF();

            marcador.put(nombre, 0);
            System.out.println(nombre + " se unio al juego.");

            // recorre cada pregunta una por una
            for (int i = 0; i < preguntas.length; i++) {

                // Manda la pregunta al jugador
                dos.writeUTF("Pregunta" + (i + 1) + preguntas[i]);

                // Espera la respuesta del jugador
                String respuestaJugador = dis.readUTF();

                // Compara ignorando mayusculas y espacios
                if (respuestaJugador.trim().equalsIgnoreCase(respuestas[i])) {

                    //Respuesta correcta: suma 1 punto
                    int puntosActuales = marcador.get(nombre);
                    marcador.put(nombre, puntosActuales + 1);
                    dos.writeUTF("Correcto! llevas" + marcador.get(nombre) + " puntos.");

                } else {
                    //Respuesta incorrecta: muestra la correcta
                    dos.writeUTF("Incorreco la respuesta era: " + respuestas[i]);
                }

            }

            // Juego terminado, manda puntaje final al jugador
            dos.writeUTF("Juego terminado! Tu puntaje final: " + marcador.get(nombre) + "/" + preguntas.length);

            // Muestra el marcador completo al jugador
            dos.writeUTF(obtenerMarcador());

            // Muestra marcador en consola del servidor
            System.out.println("--- Marcador actualizado ---");
            System.out.println(obtenerMarcador());


        } catch (IOException e) {
            System.out.println("Jugador desconectado: " + nombre);
        } finally {
            try {
                dis.close();
                dos.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Construye el marcador como texto para mostrarlo
    String obtenerMarcador() {
        StringBuilder sb = new StringBuilder("=== MARCADOR ===\n");
        for (String jugador : marcador.keySet()) {
            sb.append(jugador).append(": ").append(marcador.get(jugador)).append(" puntos\n");
        }
        return sb.toString();
    }
}