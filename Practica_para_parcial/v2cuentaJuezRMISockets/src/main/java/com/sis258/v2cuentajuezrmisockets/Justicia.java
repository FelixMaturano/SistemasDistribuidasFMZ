/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.v2cuentajuezrmisockets;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
public class Justicia extends UnicastRemoteObject implements IJusticia {
    RespuestaCuenta r = new RespuestaCuenta();
    public Justicia() throws RemoteException {
    }
    @Override
    public RespuestaCuenta consultar(String ci) throws RemoteException {
        try {
            // TCP
            Socket s = new Socket("localhost", 5000);
            PrintStream out = new PrintStream(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out.println(ci);
            String resp = in.readLine();
            if (resp != null && !resp.trim().isEmpty() && resp.contains("-")) {
                resp = resp.trim();
                String[] cuentas = resp.split(":");
                for (String c : cuentas) {
                    String[] d = c.split("-");
                    if (d.length == 2) {
                        try {
                            r.getCuentas().add(
                                    new Cuenta("A", d[0].trim(), Double.parseDouble(d[1].trim()))
                            );
                        } catch (NumberFormatException ex) {
                            System.out.println("Formato de saldo inválido en TCP: " + d[1]);
                        }
                    }
                }
            }
            s.close();
        } catch (Exception e) {
        }
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] b = ("Consultar:" + ci).getBytes();
            DatagramPacket p = new DatagramPacket(b, b.length, InetAddress.getByName("localhost"), 6000);
            socket.send(p);
            byte[] buffer = new byte[200];
            DatagramPacket resp = new DatagramPacket(buffer, buffer.length);
            socket.receive(resp);
            // 🔥 CORRECCIÓN: Agregar .trim() a la respuesta UDP cruda
            String data = new String(resp.getData(), 0, resp.getLength()).trim();
            // 🔥 CORRECCIÓN: Validar que contenga el separador "-"
            if (!data.isEmpty() && data.contains("-")) {
                String[] cuentas = data.split(":");
                for (String c : cuentas) {
                    String[] d = c.split("-");
                    // 🔥 CORRECCIÓN: Verificar longitud
                    if (d.length == 2) {
                        try {
                            r.getCuentas().add(
                                    new Cuenta("B", d[0].trim(), Double.parseDouble(d[1].trim()))
                            );
                        } catch (NumberFormatException ex) {
                            System.out.println("Formato de saldo inválido en UDP: " + d[1]);
                        }
                    }
                }
            }
            socket.close();
        } catch (Exception e) {
        }
        if (r.getCuentas().isEmpty()) {
            r.setError(true); // 🔥 Buena práctica: usar el atributo error que definiste en la clase
            r.setMensaje("No existen cuentas");
        } else {
            r.setError(false);
            r.setMensaje("Consulta exitosa");
        }
        return r;
    }
   @Override
    public double totalSaldo(String ci) throws RemoteException {
        RespuestaCuenta r = consultar(ci);
        double total = 0;
        for (Cuenta c : r.getCuentas()) {
            total += c.getSaldo();
        }
        return total;
    }
}
