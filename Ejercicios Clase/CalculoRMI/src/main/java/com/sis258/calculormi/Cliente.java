/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.calculormi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Felix
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            ICalculo calculos;

            calculos = (ICalculo) Naming.lookup("rmi://localhost/Calculo"); // instanciar un objeto 

            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            do {
                System.out.println("===Menu====");
                System.out.println("1. Factorial");
                System.out.println("2. Fibonnaci");
                System.out.println("3. Sumatoria");
                System.out.println("Seleccione una opcion: ");
                opcion = scanner.nextInt();

                if (opcion >= 1 && opcion <= 2) {
                    System.out.print("Ingrese el numero: ");
                    int numero = scanner.nextInt();

                    switch (opcion) {
                        case 1:
                            System.out.println("Factorial de " + numero + " = " + calculos.factorial(numero));
                            break;
                        case 2:
                            System.out.println("Factorial de " + numero + " = " + calculos.fibonacci(numero));
                            break;
                        case 3:
                            System.out.println("Factorial de " + numero + " = " + calculos.sumatoria(numero));
                            break;
                    }
                } else if (opcion != 0) {
                    System.out.println("opcion no valida");
                }

            } while (opcion != 0);
            System.out.println("saliendo...");
            scanner.close();
        } catch (NotBoundException ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (MalformedURLException ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (RemoteException ex) {
            System.getLogger(Cliente.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
}
