package com.sis258.verificardatostcp.udp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.net.*;
import java.io.*;
/**
 *
 * @author Ruta Binar
 */
public class ClientUDP {

    public static void main(String[] args) {
        int port =  6000;
         DatagramSocket socketUDP;
        try{
            String ip = "localhost";
            socketUDP = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            String datos = "nombre:1234567"; 
            byte[] buffer = datos.getBytes();
           DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, serverAddress, port);
            socketUDP.send(paquete);
      
            // Recibir respuesta
            byte[] respuestaBuffer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(respuestaBuffer, respuestaBuffer.length);
            socketUDP.receive(respuesta);
            String respuestaStr = new String(respuesta.getData(), 0, respuesta.getLength());
            
            System.out.println("Respuesta del servidor: " + respuestaStr);
        }catch(IOException e){
            System.out.println("Socket "+e.getMessage());
        }
        
    }
    
}
