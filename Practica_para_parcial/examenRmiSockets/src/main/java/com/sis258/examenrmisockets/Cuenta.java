/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.examenrmisockets;

import java.io.Serializable;
/**
 *
 * @author Ruta Binar
 */
// Viajan de servidor a cliente mediante RMI (serializacion)
public class Cuenta implements Serializable{
    String banco;
    String nro;
    String saldo;

    public Cuenta(String banco, String nro, String saldo) {
        this.banco = banco;
        this.nro = nro;
        this.saldo = saldo;
    }
    
    public String toString(){
        return  banco + " - " + nro + " - " + saldo;
    }
}
