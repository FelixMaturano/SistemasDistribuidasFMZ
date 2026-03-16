/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sis258.practicasocketsrmi;

import java.rmi.Remote;
/**
 *
 * @author Ruta Binar
 */
public interface IJusticia extends Remote{
    
    // que metodos tiene 
      RespuestaCuenta ConsultarCuentas(String ci, String nombres, String apellidos);
      Boolean Congelar(Cuenta cuenta,Float monto);  
    
}
