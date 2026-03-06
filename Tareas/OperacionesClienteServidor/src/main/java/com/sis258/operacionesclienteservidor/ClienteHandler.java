/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.operacionesclienteservidor;

/**
 *
 * @author Felix
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class ClienteHandler extends Thread {

    final Socket s;
    final BufferedReader dis;
    final PrintStream dos;

    public ClienteHandler(Socket s, BufferedReader dis, PrintStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            while (true) {

                // ===============================
                // PASO 1: pedir primer numero
                // ===============================
                dos.println("Introduzca el primer numero (o 'Exit' para salir):");

                String recibido = dis.readLine();

                if (recibido == null || recibido.equals("Exit")) {
                    System.out.println("Cliente " + this.s + " salio.");
                    break;
                }

                int numero1 = Integer.parseInt(recibido);

                // ===============================
                // PASO 2: pedir segundo numero
                // ===============================
                dos.println("Introduzca el segundo numero:");

                String recibido2 = dis.readLine();
                int numero2 = Integer.parseInt(recibido2);

                // ===============================
                // PASO 3: pedir operacion
                // ===============================
                dos.println("Operaciones: suma, resta, multi, div");

                String operacion = dis.readLine();

                int resultado = 0;
                boolean error = false;

                switch (operacion) {
                    case "suma":
                        resultado = numero1 + numero2;
                        break;
                    case "resta":
                        resultado = numero1 - numero2;
                        break;
                    case "multi":
                        resultado = numero1 * numero2;
                        break;
                    case "div":
                        if (numero2 == 0) {
                            dos.println("Error: division por cero");
                            error = true;
                            break;
                        }
                        resultado = numero1 / numero2;
                        break;
                    default:
                        dos.println("Operacion no valida");
                        error = true;
                        break;
                }

                // ===============================
                // PASO 4: enviar resultado
                // ===============================
                if (!error) {
                    dos.println("Resultado: " + resultado);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error con cliente " + this.s + ": " + e.getMessage());
        } finally {
            try {
                this.s.close();
                System.out.println("Conexion cerrada: " + this.s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
