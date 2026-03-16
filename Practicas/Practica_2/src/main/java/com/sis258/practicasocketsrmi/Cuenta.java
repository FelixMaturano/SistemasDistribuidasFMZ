/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sis258.practicasocketsrmi;

import java.io.Serializable;

/**
 * @author Ruta Binar
 */
public class Cuenta implements Serializable {

    private Banco banco; 

    private String nroCuenta;
    private String ci;
    private String nombres;
    private String apellidos;
    private Double saldo;

    public Cuenta(Banco banco, String nroCuenta, String ci, String nombres, String apellidos, Double saldo) {
        this.banco = banco;
        this.nroCuenta = nroCuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public String getCi() {
        return ci;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Banco getBanco() {
        return banco; // Ahora coincide
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setBanco(Banco banco) {
        this.banco = banco; // Ahora coincide
    }
}