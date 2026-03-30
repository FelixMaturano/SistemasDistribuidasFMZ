package com.sis258.sistemavotacionred;

import java.util.Scanner;

/**
 * @author Ruta Binar
 */
public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingresa tu nombre de nodo: ");
            String nombre = sc.nextLine();
            
            // Forzar el uso de IPv4 (recomendado en JGroups para evitar problemas de conexión)
            System.setProperty("java.net.preferIPv4Stack", "true");
            
            // Instanciar e iniciar el sistema de votación
            new VotingSystem(nombre).start();
            
        } catch (Exception ex) {
            // CORREGIDO: Ahora usa Main.class.getName() en lugar de Principal
            System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}