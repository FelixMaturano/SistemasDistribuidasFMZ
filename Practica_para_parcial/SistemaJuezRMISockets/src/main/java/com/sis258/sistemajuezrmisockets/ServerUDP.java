/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sistemajuezrmisockets;

import java.net.*;

/**
 *
 * @author Ruta Binar
 */
public class ServerUDP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6000);
        System.out.println("Banco B (UDP) listo...");
        while (true) {
            byte[] buffer = new byte[100];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            String ci = new String(paquete.getData(), 0, paquete.getLength());
            // Respuesta por defecto: vacía (significa "cuenta no encontrada")
            String respuesta = "";
            // Simula lógica del banco: solo la cédula "123" tiene cuenta asociada
            if (ci.equals("123")) {
                respuesta = "222-7000";   // Formato: numeroCuenta - saldo
            }
            // Convierte la respuesta (vacía o no) a bytes para enviar por UDP
            byte[] envio = respuesta.getBytes();
            DatagramPacket paqueteResp = new DatagramPacket(
                    envio,
                    envio.length,
                    paquete.getAddress(), // ← IP del cliente
                    paquete.getPort() // ← puerto del cliente
            );
            // Envía la respuesta al cliente
            socket.send(paqueteResp);
        }
    }
}
