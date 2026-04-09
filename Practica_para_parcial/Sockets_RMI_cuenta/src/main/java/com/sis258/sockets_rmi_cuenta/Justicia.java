/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sockets_rmi_cuenta;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;
import java.net.*;

/**
 *
 * @author Ruta Binar
 */
public class Justicia extends UnicastRemoteObject implements IJusticia {

    public Justicia() throws RemoteException {
    }

    @Override
    public RespuestaCuenta consultar(String ci) throws RemoteException {
        RespuestaCuenta r = new RespuestaCuenta();
        try {
            Socket s = new Socket("localhost", 5002);
            PrintStream out = new PrintStream(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out.println(ci);
            String resp = in.readLine();

            if (!resp.isEmpty()) {
                String[] datos = resp.split("-");
                r.cuentas.add(new Cuenta("MERCANTIL", datos[0], Double.parseDouble(datos[1])));
            }
            s.close();
        } catch (Exception e) {
            System.out.println("Error TCP");
        }

        try {
            DatagramSocket socket = new DatagramSocket();

            String msg = ci;
            byte[] buffer = msg.getBytes();

            DatagramPacket p = new DatagramPacket(buffer, buffer.length,
                    InetAddress.getByName("localhost"), 6789);

            socket.send(p);

            byte[] buffer2 = new byte[100];
            DatagramPacket resp = new DatagramPacket(buffer2, buffer2.length);

            socket.receive(resp);

            String data = new String(resp.getData(), 0, resp.getLength());

            if (!data.isEmpty()) {
                String[] datos = data.split("-");
                r.cuentas.add(new Cuenta("BCP", datos[0], Double.parseDouble(datos[1])));
            }

            socket.close();
        } catch (Exception e) {
            System.out.println("Error UDP");
        }
        return r;
    }

}
