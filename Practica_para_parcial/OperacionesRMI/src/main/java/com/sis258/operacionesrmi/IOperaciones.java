/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sis258.operacionesrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Ruta Binar
 */
public interface IOperaciones extends Remote {
    // 2. Cada método debe lanzar java.rmi.RemoteException
    int calcularFactorial(int numero) throws RemoteException;
    
    int calcularFibonacci(int numero) throws RemoteException;
    
    int calcularSumatoria(int numero) throws RemoteException;
}
