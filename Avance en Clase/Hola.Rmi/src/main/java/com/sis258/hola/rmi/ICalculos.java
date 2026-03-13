/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sis258.hola.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Felix
 */
public interface ICalculos extends Remote {
    
    public long factorial(int n) throws RemoteException;
    public long fibonacci(int n) throws RemoteException;
    public long sumatoria(int n) throws RemoteException;
    
}