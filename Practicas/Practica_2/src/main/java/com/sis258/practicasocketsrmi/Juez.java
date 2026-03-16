/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.practicasocketsrmi;

import java.rmi.Naming;
import java.util.Scanner;
/**
 *
 * @author Ruta Binar
 */
public class Juez {
    //hola rmi 

    public static void main(String[] args) {
        try {
            
            IJusticia servidorJusticia = (IJusticia) Naming.lookup("rmi://localhost/Justicia");

            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Sistema judicial - JUEZ ");
            
            // 2. Pedimos los datos del caso de prueba
            System.out.print("Ingrese el CI ): ");
            String ci = scanner.nextLine();
            
            System.out.print("Ingrese Nombres : ");
            String nombres = scanner.nextLine();
            
            System.out.print("Ingrese Apellidos: ");
            String apellidos = scanner.nextLine();

            System.out.println("\nEnviando solicitud al Servidor de Justicia...");

            RespuestaCuenta respuesta = servidorJusticia.ConsultarCuentas(ci, nombres, apellidos);

            System.out.println("RESULTADO DE LA BUSQUEDA-------");

            
            if (respuesta.isError()) {
                System.out.println("Hubo un problema: " + respuesta.getMensaje());
            } else {
                System.out.println(respuesta.getMensaje());
                System.out.println("Cuentas encontradas para CI " + ci + ":\n");
                
                if (respuesta.getListaCuentas().isEmpty()) {
                    System.out.println("No se encontraron cuentas bancarias.");
                } else {
                    for (Cuenta c : respuesta.getListaCuentas()) {
                        System.out.println("-> Banco: " + c.getBanco() + 
                                           " | Nro Cuenta: " + c.getNroCuenta() + 
                                           " | Saldo: " + c.getSaldo());
                    }
                }
            }
            System.out.println("\n");

        } catch (Exception e) {
            System.out.println("Error de conexion en el Juez: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
