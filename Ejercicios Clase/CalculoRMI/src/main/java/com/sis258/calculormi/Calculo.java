/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.calculormi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Calculo extends UnicastRemoteObject implements ICalculo {

    public Calculo() throws RemoteException {
        super();
    }
    @Override
    public long factorial(int n) throws RemoteException {
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
    @Override
    public long fibonacci(int n) throws RemoteException {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    @Override
    public long sumatoria(int n) throws RemoteException {
        if (n <= 0) {
            return 0;
        }
        return n + sumatoria(n - 1);
    }
}
