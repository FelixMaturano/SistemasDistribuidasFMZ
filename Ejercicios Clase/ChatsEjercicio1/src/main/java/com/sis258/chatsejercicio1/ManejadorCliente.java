/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.chatsejercicio1;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Felix
 */
public class ManejadorCliente extends Thread {// Es un hilo por eso "extends Thread"

    Socket s;                // Conexion con el cliente
    DataInputStream dis;     //Canal para leer 
    DataOutputStream dos;
    String nombre;

    static List<ManejadorCliente> listaClientes = new ArrayList<>();

    public ManejadorCliente(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            dos.writeUTF("Escribe tu nombre: ");
            nombre = dis.readUTF();

            enviarATodos(nombre + " se ha unido al chat.");

            String mensaje;
            while (true) {

                mensaje = dis.readUTF();

                if (mensaje.equals("Exit")) {
                    enviarATodos(nombre + " ha salido del chat.");
                    listaClientes.remove(this);
                    s.close();
                    break;
                }

                enviarATodos(nombre + ": " + mensaje);
            }

        } catch (IOException e) {
            listaClientes.remove(this);
            System.out.println("Cliente desconectado: " + nombre);
        }
        try {
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void enviarATodos(String mensaje) {
        System.out.println(mensaje);
        for (ManejadorCliente cliente : listaClientes) {
            try {
                cliente.dos.writeUTF(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
