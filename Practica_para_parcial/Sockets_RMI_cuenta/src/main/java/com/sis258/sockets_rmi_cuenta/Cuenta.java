/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.sockets_rmi_cuenta;

import java.io.Serializable;


/**
 *
 * @author Ruta Binar
 */
public class Cuenta implements Serializable{
    
    private String banco;
    private String nroCuenta;
    private double saldo;

    public Cuenta(String banco, String nroCuenta, double saldo) {
        this.banco = banco;
        this.nroCuenta = nroCuenta;
        this.saldo = saldo;
    }

    public String toString() {
        return banco + " | Cuenta: " + nroCuenta + " | Saldo: " + saldo;
    }
}
