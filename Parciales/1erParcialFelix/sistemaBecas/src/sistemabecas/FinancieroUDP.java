/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabecas;
import java.net.*;
/**
 *
 * @author PC
 */
public class FinancieroUDP {
    public static void main(String[] args)throws Exception{
        DatagramSocket socket = new DatagramSocket(6000);
        System.out.println("Financiero listo.......");
        
        while(true){
            byte[] buffer = new byte[100];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            
            String mensajeRecibido = new String(paquete.getData(),0,paquete.getLength()).trim();
            
            String ci = mensajeRecibido.replace("Consultar", "").trim();
            
            String respuesta="";
        }
        
        
    }
    
}
