/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.sockettcpmulti;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.sql.ClientInfoStatus;

/**
 *
 * @author Ruta Binar
 */
public class SocketTcpMulti {

    public static void main(String[] args)throws IOException {
        
        ServerSocket ss = new ServerSocket(5056);
        
        while (true){
            Socket s = null;
            try{
                // Conexion del cliente
                s = ss.accept();
                System.out.println("un nuevo cliente se ha conectado");
                
                // obtener su entrada y salida de stream
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
                System.out.println(" Asignar un nuevo hilo para este cliente");
                
                Thread t = new ClientHandler(s, dis, dos);
                
                t.start();
                      
            }catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}
