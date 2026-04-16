/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabecas;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author PC
 */
public class FinancieroUDP {

    public static void main(String[] args) throws Exception {

        try {
            DatagramSocket socket = new DatagramSocket(6000);
            System.out.println("Servidor Financiero UDP iniciado .......");
            byte[] buffer = new byte[100];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);

            String ciEstudiante = new String(paquete.getData(), 0, paquete.getLength()).trim();

            String ci = ciEstudiante.replace("Consultar", "").trim();

            String respuesta = "con deuda";
            if (ciEstudiante.equals("1234567")) {
                respuesta = "sin deuda";
            }
            
            byte[] mensajeResp = respuesta.getBytes();
            DatagramPacket resp = new DatagramPacket(mensajeResp,mensajeResp.length, paquete.getAddress(),paquete.getPort());
                    
            socket.send(resp);
            
        } catch (IOException e) {
            System.out.println("Error en FinancieroUDP: " + e.getMessage());
        }

    }

}
