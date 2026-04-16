/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.sis258.verificardatostcp.udp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
/**
 *
 * @author Ruta Binar
 */
public class ClienteTCP {

    public static void main(String[] args) {
        
        int port = 5000;
        try{
            Socket client = new Socket("localhost",port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            String datos = "nombre:1234567";
            //toServer.println("El Cliente envio : " + datos);
            toServer.println(datos);
            String result = fromServer.readLine();
            System.out.println("Cadena devuelto por el servidor es: "+result);

            client.close();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
}
