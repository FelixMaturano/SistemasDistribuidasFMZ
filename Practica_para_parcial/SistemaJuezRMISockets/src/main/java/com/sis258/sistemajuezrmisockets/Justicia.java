/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sistemajuezrmisockets;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Justicia extends UnicastRemoteObject implements IJusticia {

    public Justicia() throws RemoteException {
    }

    @Override
    public ArrayList<Cuenta> consultar(String ci) throws RemoteException {
        ArrayList<Cuenta> lista = new ArrayList<>();
        //Consulta TCP 
        try {
            Socket socket = new Socket("localhost", 5000);
            // flujo de salida para enviar texto al banco
            PrintStream out = new PrintStream(socket.getOutputStream());
            // Flujo de entrada leer respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Envia ci al banco
            out.println(ci);

            // Espera y lee la linea de respuestas
            String respuesta = in.readLine();

            // Si la respuesta no es nula ni vacia se proc..
            if (respuesta != null && !respuesta.isEmpty()) {
                String[] datos = respuesta.split("-");
                String nro = datos[0];
                double saldo = Double.parseDouble(datos[1]);

                Cuenta cuenta = new Cuenta("Banco A", nro, saldo);
                lista.add(cuenta);
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Error TCP: " + e.getMessage());
        }
        //consulta UDP banco B
        try {
            DatagramSocket socketUDP = new DatagramSocket();
            // convierte a bytes
            byte[] envio = ci.getBytes();
            // Construye el datagrama de envio: datos, longitud, destino localhost
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    envio,
                    envio.length,
                    InetAddress.getByName("localhost"),
                    6000);
            // Envia el datagrama al Ban..
            socketUDP.send(paqueteEnvio);
            // Buffer para recibir la respuesta (máximo 100 bytes)
            byte[] buffer = new byte[100];
            // Paquete vacío que se llenará con la respuesta
            DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
            // Espera la respuesta UDP (bloqueante)
            socketUDP.receive(paqueteRespuesta);

            // Convierte los bytes recibidos a String (solo hasta la longitud real)
            String respuesta = new String(
                    paqueteRespuesta.getData(),
                    0,
                    paqueteRespuesta.getLength()
            );
            // Si la respuesta no está vacía, se procesa
            if (!respuesta.isEmpty()) {
                // Separa "nro-saldo"
                String[] datos = respuesta.split("-");
                String nro = datos[0];
                double saldo = Double.parseDouble(datos[1]);

                // Crea cuenta con banco "Banco B"
                Cuenta cuenta = new Cuenta("Banco B", nro, saldo);
                lista.add(cuenta);
            }
            socketUDP.close();
        } catch (Exception e) {
            System.out.println("Error UDP: " + e.getMessage());
        }
        return lista;
    }
    @Override
    public boolean congelar(Cuenta c, double monto) throws RemoteException {
        // Verifica si la cuenta tiene saldo suficiente
        if (c.getSaldo() >= monto) {
            // Calcula el nuevo saldo restando el monto
            double nuevoSaldo = c.getSaldo() - monto;
            // Actualiza el saldo de la cuenta (en memoria, no persiste)
            c.setSaldo(nuevoSaldo);
            // Retorna true indicando que se congeló el monto
            return true;
        }
        // Saldo insuficiente, no se modifica nada
        return false;
    }

}
