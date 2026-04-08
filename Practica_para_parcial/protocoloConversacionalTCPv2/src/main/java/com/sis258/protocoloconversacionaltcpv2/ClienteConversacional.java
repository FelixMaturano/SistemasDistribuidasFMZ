package com.sis258.protocoloconversacionaltcpv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteConversacional {

    public static void main(String[] args) {
        int port = 5003;

        try {
            Socket client = new Socket("localhost", port);

            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));

            PrintStream toServer = new PrintStream(client.getOutputStream());

            Scanner sc = new Scanner(System.in);

            while (true) {

                // ✅ (SIN CAMBIO) leer mensaje del servidor
                String mensaje = fromServer.readLine();

                if (mensaje == null) {
                    break;
                }

                System.out.println("Servidor: " + mensaje);

                // 🔥 🔥 🔥 CAMBIO IMPORTANTE 🔥 🔥 🔥
                // 👉 SI ES RESPUESTA FINAL, NO ENVIAMOS NADA
                if (mensaje.startsWith("Resultado") ||
                    mensaje.startsWith("Error") ||
                    mensaje.startsWith("Operacion invalida")) {
                    
                    continue;   // 🔥 ANTES NO EXISTÍA
                }

                // 🔥 🔥 🔥 CAMBIO IMPORTANTE 🔥 🔥 🔥
                // 👉 SOLO RESPONDEMOS CUANDO EL SERVIDOR PREGUNTA
                String respuesta = sc.nextLine();
                toServer.println(respuesta);

                // ❌ ELIMINADO (ESTE ERA EL ERROR)
                /*
                String respuestaServidor = fromServer.readLine();
                if (respuestaServidor == null) {
                    break;
                }

                if (respuestaServidor.startsWith("Resultado")
                        || respuestaServidor.startsWith("Error")
                        || respuestaServidor.startsWith("Operacion")) {

                    System.out.println("Servidor: " + respuestaServidor);
                } else {
                    System.out.println("Servidor: " + respuestaServidor);
                }
                */
            }

            client.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}