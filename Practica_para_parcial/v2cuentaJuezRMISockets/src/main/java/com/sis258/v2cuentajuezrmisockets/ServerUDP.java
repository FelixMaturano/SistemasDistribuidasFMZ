package com.sis258.v2cuentajuezrmisockets;

import java.net.*;

public class ServerUDP {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6000);
        System.out.println("Banco B (UDP) listo...");
        
        while (true) {
            byte[] buffer = new byte[100];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            
            // 🔥 CORRECCIÓN 1: Capturamos el mensaje completo y le hacemos trim()
            String mensajeRecibido = new String(paquete.getData(), 0, paquete.getLength()).trim();
            
            // 🔥 CORRECCIÓN 2: Extraemos solo la cédula (quitamos el "Consultar:")
            String ci = mensajeRecibido.replace("Consultar:", "").trim();
            
            String respuesta = "";
            
            // Ahora sí la comparación funcionará perfectamente
            if (ci.equals("123")) {
                respuesta = "222-7000";   // Formato: numeroCuenta-saldo
            }
            
            byte[] envio = respuesta.getBytes();
            DatagramPacket paqueteResp = new DatagramPacket(
                    envio,
                    envio.length,
                    paquete.getAddress(),
                    paquete.getPort()
            );
            
            socket.send(paqueteResp);
        }
    }
}