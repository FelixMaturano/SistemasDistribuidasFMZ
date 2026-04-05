/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.operacionesrmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author Ruta Binar
 */
public class ServidorOperaciones extends UnicastRemoteObject implements IOperaciones{

    public ServidorOperaciones() throws RemoteException {
    }

// --- IMPLEMENTACIÓN DE LOS MÉTODOS ---
    @Override
    public int calcularFactorial(int numero) throws RemoteException {
        System.out.println("Servidor: Calculando factorial de " + numero);
        int fact = 1;
        for (int i = 1; i <= numero; i++) {
            fact *= i;
        }
        return fact;
    }

    @Override
    public int calcularFibonacci(int numero) throws RemoteException {
        System.out.println("Servidor: Calculando Fibonacci de " + numero);
        if (numero <= 1) return numero;
        return calcularFibonacci(numero - 1) + calcularFibonacci(numero - 2);
    }

    @Override
    public int calcularSumatoria(int numero) throws RemoteException {
        System.out.println("Servidor: Calculando sumatoria de " + numero);
        int suma = 0;
        for (int i = 1; i <= numero; i++) {
            suma += i;
        }
        return suma;
    }
    public static void main(String[] args) {
    try {
            // 2. Creamos el directorio telefónico (Registry) en el puerto 1099
            Registry registro = LocateRegistry.createRegistry(1099);
            
            // 3. Instanciamos nuestro objeto servidor
            ServidorOperaciones servidor = new ServidorOperaciones();
            
            // 4. Publicamos el objeto en el registro con un nombre ("Operaciones")
            registro.rebind("Operaciones", servidor);
            
            System.out.println("¡Servidor RMI de Operaciones listo y esperando peticiones!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
