/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sis258.operacionesrmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
/**
 *
 * @author Ruta Binar
 */
public class ClienteOperaciones {

    public static void main(String[] args) {
        try{
            // 1. Buscamos el directorio telefónico (Registry) en la IP del servidor y puerto 1099
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            
            // 2. Buscamos al objeto por su nombre y hacemos un "cast" a la Interfaz
            IOperaciones operaciones = (IOperaciones) registro.lookup("Operaciones");
            
            Scanner sc = new Scanner(System.in);
            int opcion = 0;
            
            // 3. Menú interactivo
            while (opcion != 4) {
                System.out.println("\n--- MENÚ RMI ---");
                System.out.println("1. Factorial");
                System.out.println("2. Fibonacci");
                System.out.println("3. Sumatoria");
                System.out.println("4. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                
                if (opcion >= 1 && opcion <= 3) {
                    System.out.print("Introduce el número n: ");
                    int n = sc.nextInt();
                    
                    // ¡AQUÍ OCURRE LA MAGIA RMI! Llamamos al método como si fuera local
                    int resultado = 0;
                    if (opcion == 1) resultado = operaciones.calcularFactorial(n);
                    if (opcion == 2) resultado = operaciones.calcularFibonacci(n);
                    if (opcion == 3) resultado = operaciones.calcularSumatoria(n);
                    
                    System.out.println("-> El resultado devuelto por el servidor es: " + resultado);
                }
            }
            sc.close();
            System.out.println("Cliente desconectado.");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
