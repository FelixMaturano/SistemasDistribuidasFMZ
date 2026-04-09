/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sis258.sockets_rmi_cuenta;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Ruta Binar
 */
public interface IJusticia extends Remote{
   RespuestaCuenta consultar(String ci)throws RemoteException;
}
