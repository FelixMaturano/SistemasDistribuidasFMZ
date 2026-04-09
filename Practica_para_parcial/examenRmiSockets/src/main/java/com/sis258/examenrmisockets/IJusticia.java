/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sis258.examenrmisockets;

import java.rmi.Remote;  
import java.rmi.RemoteException; 
import java.util.ArrayList;   
/**
 *
 * @author Ruta Binar
 */
public interface IJusticia extends Remote {
    ArrayList<Cuenta> consultar(String ci) throws RemoteException;
    boolean congelar(Cuenta c, double monto) throws RemoteException;
    
}
