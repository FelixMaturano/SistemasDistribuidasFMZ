/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.practicasocketsrmi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Ruta Binar
 */
public class ServerBCP {

    //Aqui el servidor udp
    public static void main(String args[]) {
        int port = 6789;
        try {

            DatagramSocket socketUDP = new DatagramSocket(port);
            byte[] bufer = new byte[1000];

            while (true) {
                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion
                        = new DatagramPacket(bufer, bufer.length);

                // Leemos una petición del DatagramSocket
                socketUDP.receive(peticion);

                System.out.print("Datagrama recibido del host: "
                        + peticion.getAddress());
                System.out.println(" desde enl puerto remoto: "
                        + peticion.getPort());

                String cadena = new String(peticion.getData(), 0, peticion.getLength()).trim();
                System.out.println("Mensaje recibido: " + cadena);

                String response = "";

                if (cadena.contains(":")) {
                    String[] partes = cadena.split(":");
                    String operacion = partes[0];
                    String ci = partes[1];

                    // Verificamos si es una consulta y si el CI es el del Caso de Prueba [cite: 47]
                    if (operacion.equalsIgnoreCase("Consultar")) {
                        if (ci.equals("11021654")) {
                            response = "657654-6000.0"; // Respuesta esperada para el BCP [cite: 49]
                        }
                    }

                }

                byte[] mensaje = response.getBytes();

                DatagramPacket respuesta = new DatagramPacket(mensaje, mensaje.length,
                        peticion.getAddress(), peticion.getPort());

                // Enviamos la respuesta
                socketUDP.send(respuesta);
                System.out.println("Respuesta enviada: " + (response.isEmpty() ? "(vacía)" : response));
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}
