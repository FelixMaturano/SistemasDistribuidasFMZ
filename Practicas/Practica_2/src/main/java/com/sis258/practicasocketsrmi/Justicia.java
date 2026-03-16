/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.practicasocketsrmi;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Ruta Binar
 */
public class Justicia extends UnicastRemoteObject implements IJusticia {

    public Justicia() throws RemoteException {
        super();
    }

    @Override
    public RespuestaCuenta ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {

        System.out.println("\n[Justicia] Juez solicito consulta para CI: " + ci);

        // 1. Preparamos el objeto de respuesta que devolveremos al Juez
        RespuestaCuenta respuesta = new RespuestaCuenta();
        respuesta.setListaCuentas(new ArrayList<>());
        respuesta.setError(false);
        respuesta.setMensaje("Consulta finalizada.");

        try {
            System.out.println("[Justicia] Contactando al Banco Mercantil (TCP)...");
            Socket socketTCP = new Socket("localhost", 5002);
            PrintStream outTCP = new PrintStream(socketTCP.getOutputStream());
            BufferedReader inTCP = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()));

            // Según tu PDF, al Mercantil solo se le envía el CI
            outTCP.println(ci);

            // Recibimos la respuesta (ej. "1515-5200.0" o "")
            String respMercantil = inTCP.readLine();

            if (respMercantil != null && !respMercantil.isEmpty()) {
                // Si hay datos, los partimos. Primero por ":" (por si hay múltiples cuentas)
                String[] cuentas = respMercantil.split(":");
                for (String c : cuentas) {
                    // Luego partimos por "-" para separar cuenta de saldo
                    String[] datos = c.split("-");
                    String nroCuenta = datos[0];
                    Double saldo = Double.parseDouble(datos[1]);

                    // Creamos el objeto Cuenta y lo añadimos a nuestra lista
                    Cuenta cuentaMercantil = new Cuenta(Banco.MERCANTIL, nroCuenta, ci, nombres, apellidos, saldo);
                    respuesta.getListaCuentas().add(cuentaMercantil);
                }
                System.out.println("[Justicia] Datos obtenidos del Mercantil.");
            } else {
                System.out.println("[Justicia] Mercantil no tiene registros de este CI.");
            }
            socketTCP.close();

        } catch (Exception e) {
            System.out.println("Error comunicando con Mercantil: " + e.getMessage());
        }

        try {
            System.out.println("[Justicia] Contactando al Banco BCP (UDP)...");
            DatagramSocket socketUDP = new DatagramSocket();

            // Según tu PDF, al BCP se le envía "Operación:ci"
            String mensajeBCP = "Consultar:" + ci;
            byte[] bufferEnvio = mensajeBCP.getBytes();
            InetAddress ipDestino = InetAddress.getByName("localhost");

            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipDestino, 6789);
            socketUDP.send(paqueteEnvio);

            // Preparamos para recibir la respuesta
            byte[] bufferRecepcion = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            socketUDP.receive(paqueteRecibido);

            // Limpiamos el texto recibido
            String respBCP = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength()).trim();

            if (!respBCP.isEmpty()) {
                // Hacemos exactamente el mismo despiece que con el Mercantil
                String[] cuentas = respBCP.split(":");
                for (String c : cuentas) {
                    String[] datos = c.split("-");
                    String nroCuenta = datos[0];
                    Double saldo = Double.parseDouble(datos[1]);
                    
                    Cuenta cuentaBCP = new Cuenta(Banco.BCP, nroCuenta, ci, nombres, apellidos, saldo);
                    respuesta.getListaCuentas().add(cuentaBCP);
                }
                System.out.println("[Justicia] Datos obtenidos del BCP.");
            } else {
                System.out.println("[Justicia] BCP no tiene registros de este CI.");
            }
            socketUDP.close();

        } catch (Exception e) {
            System.out.println("Error comunicando con BCP: " + e.getMessage());
        }

        // 4. Finalmente, devolvemos el "sobre" lleno de cuentas al Juez
        return respuesta;
    }

    @Override
    public boolean Congelar(Cuenta cuenta, Float monto) throws RemoteException {
        // Dejaremos esto pendiente para después de probar la consulta
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
