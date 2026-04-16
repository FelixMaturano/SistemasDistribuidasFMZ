/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.v2cuentajuezrmisockets;

import java.io.Serializable;

/**
 *
 * @author Ruta Binar
 */
public class Cuenta implements Serializable {

    private String banco;
    private String nro;
    private double saldo;

    public Cuenta(String banco, String nro, double saldo) {
        this.banco = banco;
        this.nro = nro;
        this.saldo = saldo;
    }

    public Cuenta() {
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Banco: " + banco
                + " | Cuenta: " + nro
                + " | Saldo: " + saldo;
    }
    
}
