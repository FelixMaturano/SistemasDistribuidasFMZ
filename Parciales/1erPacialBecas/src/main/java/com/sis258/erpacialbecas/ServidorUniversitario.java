/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.erpacialbecas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.net.*;

/**
 *
 * @author Ruta Binar
 */
public class ServidorUniversitario extends UnicastRemoteObject implements IBeca {

    public ServidorUniversitario() throws RemoteException {
        super();
    }

    @Override
    public RespuestaBeca SolicitarBeca(String ci, String nombres, String apellidos) throws RemoteException {
        RespuestaBeca respuesta = new RespuestaBeca();
        respuesta.setAprobado(false);
        try {
            boolean existeEnSegip = consultarSegipTCP(ci);
            boolean sinDeudas = consultaFinancieroUDP(ci);

            if (existeEnSegip && sinDeudas) {
                ArrayList<Nota> notas = new ArrayList<>();

                notas.add(new Nota("SIS-258", 80));
                notas.add(new Nota("SIS-350", 50));
                double promdeio = calcularPromedio(notas);
                for (Nota n : notas) {
                    double promedio = n.getCalificacion();
                    if (promedio > 70) {
                        respuesta = new RespuestaBeca(true, "Aprobado. identidad verificada buen promedio.", promedio, notas);
                    } else {
                        respuesta = new RespuestaBeca(false, "Rechazado. Su promedio no alcanza el mínimo requerido.", promedio, notas);
                    }
                }
            } else {
                String motivo = "Rechazado. ";
                if (!existeEnSegip) {
                    motivo += "CI no encontrado en Segip.";
                }
                if (!sinDeudas) {
                    motivo += " Presenta deudas pendientes";
                }
                respuesta.setMotivo(motivo);
            }

        } catch (Exception ex) {
            respuesta.setMotivo("Error interno del servidor " + ex.getMessage());
        }
        return respuesta;
    }

    private boolean consultarSegipTCP(String ci) {
        int port = 5000;
        try {
            Socket client = new Socket("localhost", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

            toServer.println(ci);
            String result = fromServer.readLine();
            if (result != null && result.contains("si existe")) {
                return true;
            }

        } catch (IOException e) {
            System.out.println("Error TCP Segip: " + e.getMessage());
        }
        return false;
    }
    private boolean consultaFinancieroUDP(String ci){
        int port = 6000;
        DatagramSocket socketUDP;
        try {
            socketUDP = new DatagramSocket(port);
            byte[] mensaje = ci.getBytes();
            DatagramPacket peticion = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName("localhost"), 6000);
            socketUDP.send(peticion);
            
            byte[] buffer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            socketUDP.setSoTimeout(5000); 
            socketUDP.receive(respuesta);
            
            String resultado = new String(respuesta.getData(), 0, respuesta.getLength()).trim();
            if (resultado.equals("sin deuda")) return true;
        } catch (IOException ex) {
            System.out.println("Error UDP Financiero: " + ex.getMessage());
        }
        return false;
    }
    private double calcularPromedio(ArrayList<Nota> notas){
        if(notas == null || notas.isEmpty())return 0.0;
        double suma =0;
        for(Nota nota: notas){
            suma += nota.getCalificacion();
        }
        return suma/notas.size();
    }

}
