/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.sis258.verificardatostcp.udp;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author Ruta Binar
 */
public class FinancieroUDP {

    public static void main(String[] args) {
        int port = 6000;
        DatagramSocket socketUDP;
        try {
            socketUDP = new DatagramSocket(port);
            byte[] buffer = new byte[1000];
            System.out.println("Servidor Financiero (UDP) iniciado en el puerto " + port);
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(peticion);
            String ciEstudiante = new String(peticion.getData(), 0, peticion.getLength()).trim();
            System.out.println("Financiero recibió consulta por el CI: " + ciEstudiante);
            String[] dato = ciEstudiante.split(":");
            String respuestaStr = "";
            if (dato[1].equals("1234567")) {
                respuestaStr = "sin deuda";
            }else{
                respuestaStr = "con deuda";
            }
            byte[] mensajeRespuesta = respuestaStr.getBytes();
            DatagramPacket respuesta = new DatagramPacket(
                    mensajeRespuesta, mensajeRespuesta.length,
                    peticion.getAddress(), peticion.getPort()
            );
            socketUDP.send(respuesta);
        } catch (IOException e) {
            System.out.println("Error Financiero" + e.getMessage());
        }
    }

}
