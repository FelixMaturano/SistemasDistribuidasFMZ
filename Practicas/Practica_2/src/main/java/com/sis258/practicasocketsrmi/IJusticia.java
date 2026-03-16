/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sis258.practicasocketsrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Ruta Binar
 */
public interface IJusticia extends Remote{
    
    // que metodos tiene 
      public RespuestaCuenta ConsultarCuentas(String ci, String nombres, String apellidos)throws RemoteException;
      
      boolean Congelar(Cuenta cuenta, Float monto)throws RemoteException;  
    
}
