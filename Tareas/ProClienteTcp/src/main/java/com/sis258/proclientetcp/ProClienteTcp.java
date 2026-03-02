package com.sis258.proclientetcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ProClienteTcp {

    public static void main(String[] args) {

        int port = 5002;

        try {
            Socket client = new Socket("10.13.130.220", port);

            PrintStream toServer = new PrintStream(client.getOutputStream());

            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));

            Scanner sc = new Scanner(System.in);
            while(true){
            System.out.println("Escriba la operacion (suma, resta, multi, div)o salir : ");
            String operacion = sc.nextLine();
            
            if(operacion.equals("salir")){
                break;
            }

            System.out.print("Numero 1: ");
            int n1 = sc.nextInt();

            System.out.print("Numero 2: ");
            int n2 = sc.nextInt();

            // Armamos el protocolo en texto
            String mensaje = operacion + ":" + n1 + "," + n2;

            toServer.println(mensaje);

            String resultado = fromServer.readLine();

            System.out.println("Resultado del servidor: " + resultado);
            }

            //client.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}