/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.operacionesclienteservidor;

/**
 *
 * @author Felix
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteOpMultihilo {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            InetAddress ip = InetAddress.getByName("10.235.110.70");
            Socket s = new Socket(ip, 5056);

            // usar BufferedReader y PrintStream (igual que protocolo v2)
            PrintStream toServer = new PrintStream(s.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            while (true) {

                // ===============================
                // PASO 1: primer numero
                // ===============================
                System.out.println(fromServer.readLine());  // "Introduzca el primer numero..."
                String entrada = sc.nextLine();

                if (entrada.equals("Exit")) {
                    toServer.println("Exit");
                    break;
                }

                toServer.println(entrada);

                // ===============================
                // PASO 2: segundo numero
                // ===============================
                System.out.println(fromServer.readLine());  // "Introduzca el segundo numero..."
                toServer.println(sc.nextLine());

                // ===============================
                // PASO 3: operacion
                // ===============================
                System.out.println(fromServer.readLine());  // "Operaciones: suma, resta..."
                toServer.println(sc.nextLine());

                // ===============================
                // PASO 4: resultado
                // ===============================
                System.out.println(fromServer.readLine());  // "Resultado: X"
            }

            System.out.println("Conexion cerrada.");
            s.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}