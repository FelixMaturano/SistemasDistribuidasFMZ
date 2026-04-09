/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.examenrmisockets;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Ruta Binar
 */
public class Justicia extends UnicastRemoteObject implements IJusticia {

    public Justicia() throws RemoteException {
    }

    @Override
    public ArrayList<Cuenta> consultar(String ci) throws RemoteException {
        ArrayList<Cuenta> lista = new ArrayList<>();
        try {
            Socket s = new Socket("localhost", 5000);
            PrintStream out = new PrintStream(s.getOutputStream());
            // 3. Flujo de entrada para leer respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println(ci);
            String resp = in.readLine();
            // 6. Si la respuesta no está vacía, parsea y crea una cuenta
            if (!resp.isEmpty()) {
                String[] d = resp.split("-");      // d[0] = nro cuenta, d[1] = saldo
                lista.add(new Cuenta("A", d[0], Double.parseDouble(d[1])));
            }
            s.close();
        } catch (Exception e) {
        }
        try {
            // 1. Crea un DatagramSocket (sin puerto fijo, se asigna automático)
            DatagramSocket socket = new DatagramSocket();

            // 2. Prepara el paquete de envío: datos CI + destino localhost:6000
            byte[] b = ci.getBytes();
            DatagramPacket p = new DatagramPacket(b, b.length,
                    InetAddress.getByName("localhost"), 6000);

            // 3. Envía el paquete UDP
            socket.send(p);

            // 4. Prepara buffer para recibir respuesta (máximo 100 bytes)
            byte[] buffer = new byte[100];
            DatagramPacket r = new DatagramPacket(buffer, buffer.length);

            // 5. Espera respuesta UDP
            socket.receive(r);

            // 6. Convierte la respuesta en String
            String resp = new String(r.getData(), 0, r.getLength());

            // 7. Si no vacía, parsea y agrega cuenta "B"
            if (!resp.isEmpty()) {
                String[] d = resp.split("-");
                lista.add(new Cuenta("B", d[0], Double.parseDouble(d[1])));
            }

            // 8. Cierra el socket UDP
            socket.close();
        } catch (Exception e) {
        }
         return lista;
    }

    @Override
    public boolean congelar(Cuenta c, double monto) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
