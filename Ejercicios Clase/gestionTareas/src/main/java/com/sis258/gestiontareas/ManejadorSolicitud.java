/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.gestiontareas;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Felix
 */
public class ManejadorSolicitud extends Thread {
    Socket socket; DataInputStream dis; DataOutputStream dos;
    static List<String> tareas = new ArrayList<>();

    public ManejadorSolicitud(Socket socket, DataInputStream dis, DataOutputStream dos) {
        this.socket = socket; this.dis = dis; this.dos = dos;
    }

    @Override
    public void run() {
        try {
            dos.writeUTF("=== Sistema de Gestion de Tareas ===");
            dos.writeUTF("Comandos: AGREGAR:desc | ELIMINAR:num | LISTAR | Exit");

            String comando;
            while (true) {
                comando = dis.readUTF().trim();
                if (comando.equalsIgnoreCase("Exit")) {
                    dos.writeUTF("Hasta luego!"); break;
                } else if (comando.equalsIgnoreCase("LISTAR")) {
                    dos.writeUTF(listarTareas());
                } else if (comando.toUpperCase().startsWith("AGREGAR:")) {
                    String nuevaTarea = comando.substring(8).trim();
                    if (nuevaTarea.isEmpty()) {
                        dos.writeUTF("Error: escribe una descripcion.");
                    } else {
                        synchronized (tareas) { tareas.add(nuevaTarea); }
                        dos.writeUTF("Tarea agregada."); dos.writeUTF(listarTareas());
                    }
                } else if (comando.toUpperCase().startsWith("ELIMINAR:")) {
                    try {
                        int num = Integer.parseInt(comando.substring(9).trim());
                        synchronized (tareas) {
                            if (num < 1 || num > tareas.size()) {
                                dos.writeUTF("Error: numero fuera de rango.");
                            } else {
                                String elim = tareas.remove(num - 1);
                                dos.writeUTF("Eliminada: " + elim);
                                dos.writeUTF(listarTareas());
                            }
                        }
                    } catch (NumberFormatException e) {
                        dos.writeUTF("Error: numero no valido.");
                    }
                } else {
                    dos.writeUTF("Comando no reconocido.");
                }
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado.");
        } finally {
            try { dis.close(); dos.close(); socket.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    String listarTareas() {
        synchronized (tareas) {
            if (tareas.isEmpty()) return "No hay tareas registradas.";
            StringBuilder sb = new StringBuilder("=== Lista de Tareas ===\n");
            for (int i = 0; i < tareas.size(); i++)
                sb.append(i+1).append(". ").append(tareas.get(i)).append("\n");
            return sb.toString();
        }
    }
}

